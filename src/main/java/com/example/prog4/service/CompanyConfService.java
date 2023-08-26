package com.example.prog4.service;

import com.example.prog4.entity.Employee.CompanyConf;
import com.example.prog4.repository.Employee.CompanyConfRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CompanyConfService {
  private final static int COMPANY_CONF_ID = 1;
  private final CompanyConfRepository companyConfRepository;

  public CompanyConf getCompanyConf() {
    Optional<CompanyConf> existingConf = companyConfRepository.findById(COMPANY_CONF_ID);
    if (existingConf.isPresent()) {
      return existingConf.get();
    }
    CompanyConf companyConf = new CompanyConf();
    companyConf.setId(COMPANY_CONF_ID);
    companyConf.setCompanyName("Company Name");
    companyConf.setCompanyDescription("Company Description");
    companyConf.setCompanyPhone("Company Phone");
    companyConf.setCompanySlogan("Company Slogan");
    companyConf.setCompanyEmail("Company Email");
    companyConf.setCompanyAddress("Company Address");
    companyConf.setNIF("NIF");
    companyConf.setSTAT("STAT");
    companyConf.setRCS("RCS");
    companyConf.setCompanyLogoBase64(
        null
    );

    return companyConf;
  }

  public void crupdateCompanyConf(CompanyConf companyConf, MultipartFile imageFile)
      throws IOException {

    Optional<CompanyConf> existingConf = companyConfRepository.findById(COMPANY_CONF_ID);

    if (existingConf.isPresent()) {
      CompanyConf existing = existingConf.get();

      if (companyConf.getCompanyName() != null) {
        existing.setCompanyName(companyConf.getCompanyName());
      }
      if (companyConf.getCompanyDescription() != null) {
        existing.setCompanyDescription(companyConf.getCompanyDescription());
      }
      if (companyConf.getCompanyPhone() != null) {
        existing.setCompanyPhone(companyConf.getCompanyPhone());
      }
      if (companyConf.getCompanySlogan() != null) {
        existing.setCompanySlogan(companyConf.getCompanySlogan());
      }
      if (companyConf.getCompanyEmail() != null) {
        existing.setCompanyEmail(companyConf.getCompanyEmail());
      }
      if (companyConf.getCompanyAddress() != null) {
        existing.setCompanyAddress(companyConf.getCompanyAddress());
      }
      if (companyConf.getNIF() != null) {
        existing.setNIF(companyConf.getNIF());
      }
      if (companyConf.getSTAT() != null) {
        existing.setSTAT(companyConf.getSTAT());
      }
      if (companyConf.getRCS() != null) {
        existing.setRCS(companyConf.getRCS());
      }

      if (imageFile != null && !imageFile.isEmpty()) {
        byte[] imageBytes = imageFile.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        existing.setCompanyLogoBase64(base64Image);
      }

    } else {
      byte[] imageBytes = imageFile.getBytes();
      String base64Image = Base64.getEncoder().encodeToString(imageBytes);
      companyConf.setCompanyLogoBase64(base64Image);
      companyConfRepository.save(companyConf);
    }
  }
}
