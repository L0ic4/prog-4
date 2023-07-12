package com.example.prog4.controller;

import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


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
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "employee-add";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee,@RequestParam("image") MultipartFile imageFile) {
        if(!imageFile.isEmpty()){
            try {
                byte[] imageBytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                employee.setImageBase64(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'erreur de lecture du fichier
            }
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }
}
