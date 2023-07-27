package com.example.prog4.service;

import com.example.prog4.entity.CompanyConf;
import com.example.prog4.repository.CompanyConfRepository;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CompanyConfService {
  private final CompanyConfRepository companyConfRepository;


  public void crupdateCompanyConf(CompanyConf companyConf, MultipartFile imageFile)
      throws IOException {
    byte[] imageBytes = imageFile.getBytes();
    companyConf.setCompanyLogo(imageBytes);
    companyConfRepository.save(companyConf);
  }
}
