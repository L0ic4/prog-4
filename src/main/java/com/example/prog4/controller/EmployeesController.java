package com.example.prog4.controller;
import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model, HttpSession session) {
        Iterable<EmployeeEntity> employees = employeeService.findAll();
        session.setAttribute("employees", employees);
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(EmployeeEntity employeeEntity) {
        return "employee-add";
    }
    @PostMapping("/save")
    public String saveEmployee(EmployeeEntity employeeEntity, HttpSession session) {
        session.setAttribute("newEmployee", employeeEntity);
        employeeService.save(employeeEntity);
        return "redirect:/employees";
    }
}
