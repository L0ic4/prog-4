package com.example.prog4.service;

import com.example.prog4.entity.CompanyConf;
import com.example.prog4.repository.CompanyConfRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyConfService {
  private final CompanyConfRepository companyConfRepository;


  public void crupdateCompanyConf(CompanyConf companyConf) {
    companyConfRepository.save(companyConf);
  }
}
