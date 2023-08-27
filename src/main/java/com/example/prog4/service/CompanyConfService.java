package com.example.prog4.service;

import com.example.prog4.entity.Employee.CompanyConf;
import com.example.prog4.repository.Employee.CompanyConfRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyConfService {
  private final static int COMPANY_CONF_ID = 1;
  private final CompanyConfRepository companyConfRepository;

  public CompanyConf getCompanyConf() {
    Optional<CompanyConf> existingConf = companyConfRepository.findById(COMPANY_CONF_ID);
    return existingConf.orElseGet(CompanyConf::new);
  }

  public void crupdateCompanyConf(CompanyConf companyConf) {
    companyConfRepository.save(companyConf);
  }
}
