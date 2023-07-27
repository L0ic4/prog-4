package com.example.prog4.controller;

import com.example.prog4.entity.CompanyConf;
import com.example.prog4.service.CompanyConfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@AllArgsConstructor
public class CompanyConfController {
  private final CompanyConfService companyConfService;


  @GetMapping("/conf")
  public String showAddCompanyConfForm(Model model) {
    model.addAttribute("companyConf", new CompanyConf());
    return "company-add";
  }
  @PutMapping("/crupdatecompanyconf")
  public String crupdateCompanyConf(@ModelAttribute("companyConf") CompanyConf companyConf) {
    companyConfService.crupdateCompanyConf(companyConf);
    return "redirect:/employees";
  }
}
