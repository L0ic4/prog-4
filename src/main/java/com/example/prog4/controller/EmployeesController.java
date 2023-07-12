package com.example.prog4.controller;

import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        Iterable<EmployeeEntity> employees = employeeService.findAll();
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
    public String showAddEmployeeForm(EmployeeEntity employeeEntity) {
        return "employee-add";
    }

    @PostMapping("/save")
    public String saveEmployee(EmployeeEntity employeeEntity) {
        employeeService.save(employeeEntity);
        return "redirect:/employees";
    }
}
