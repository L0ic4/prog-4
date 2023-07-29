package com.example.prog4.controller;

import com.example.prog4.entity.CompanyConf;
import com.example.prog4.service.CompanyConfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
@ControllerAdvice(annotations = Controller.class)
public class AnnotationAdvice {
  private final CompanyConfService companyConfService;

  @ModelAttribute("companyConfGlobal")
  public CompanyConf getCompanyConf() {
    return companyConfService.getCompanyConf();
  }
}
