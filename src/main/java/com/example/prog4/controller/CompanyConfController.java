package com.example.prog4.controller;

import com.example.prog4.entity.employee.CompanyConf;
import com.example.prog4.service.CompanyConfService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class CompanyConfController {
  private final CompanyConfService companyConfService;


  @GetMapping("/conf")
  public String showAddCompanyConfForm(Model model) {
    model.addAttribute("companyConf", new CompanyConf());
    return "company-add";
  }

  @PostMapping("/crupdatecompanyconf")
  public String crupdateCompanyConf(@ModelAttribute("companyConf") CompanyConf companyConf,
                                    @RequestParam("image") MultipartFile imageFile)
      throws IOException {
    companyConfService.crupdateCompanyConf(companyConf, imageFile);
    return "redirect:/employees";
  }
}
