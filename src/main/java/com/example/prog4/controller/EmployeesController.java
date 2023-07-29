package com.example.prog4.controller;

import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.service.CsvFileGenerator;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
  private final EmployeeService employeeService;
  private final CsvFileGenerator csvFileGenerator;


  @GetMapping
  public String getAllEmployees(@RequestParam(required = false) String firstname,
                                @RequestParam(required = false) String lastname,
                                @RequestParam(required = false) String sex,
                                @RequestParam(required = false) String position,
                                @RequestParam(required = false) String hiredate,
                                @RequestParam(required = false) String resigndate,
                                @RequestParam(required = false) boolean isDownload,
                                @RequestParam(required = false) String code,
                                @RequestParam(required = false, defaultValue = "asc")
                                String orderBy,
                                Model model, HttpServletResponse response,
                                HttpServletRequest request) {

    Iterable<EmployeeEntity> employees;

    if (firstname != null) {
      employees = employeeService.findEmployeesByFirstname(firstname);
    } else if (lastname != null) {
      employees = employeeService.findEmployeesByLastname(lastname);
    } else if (position != null) {
      employees = employeeService.findEmployeesByPosition(position);
    } else if (hiredate != null) {
      employees = employeeService.findEmployeesByHireDate(hiredate);
    } else if (resigndate != null) {
      employees = employeeService.findEmployeesByResignationDate(resigndate);
    } else if (code != null) {
      employees = employeeService.findByPhoneCountryCode(code);
    } else if (sex != null) {
      employees = employeeService.findEmployeesBySex(sex);
    } else {
      employees = employeeService.findAll();
    }
    if (isDownload) {
      csvFileGenerator.writeEmployeesToCsv((List<EmployeeEntity>) employees, response);
    }
    model.addAttribute("employees", employees);
    return "employee-list";
  }

  @GetMapping("/{id}")
  public String getEmployeeById(Model model, @PathVariable int id) {
    EmployeeEntity employeeEntity = employeeService.findById(id);
    model.addAttribute("employee", employeeEntity);
    return "employee-card";
  }

  @GetMapping("/add")
  public String showAddEmployeeForm(Model model) {
    model.addAttribute("employee", new EmployeeEntity());
    return "employee-add";
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee,
                             @RequestParam("image") MultipartFile imageFile,
                             @RequestParam("codes") String[] codes,
                             @RequestParam("phones") String[] phones) throws IOException {
    List<String> codesList = Arrays.asList(codes);
    List<String> phonesList = Arrays.asList(phones);
    employeeService.save(employee, imageFile, codesList, phonesList);
    return "redirect:/employees";
  }
}
