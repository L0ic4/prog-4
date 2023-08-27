package com.example.prog4.controller.Mapper;

import com.example.prog4.controller.Data.InputData.CompanyConfInput;
import com.example.prog4.entity.Employee.CompanyConf;
import com.example.prog4.service.CompanyConfService;
import java.io.IOException;
import java.util.Base64;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Component
public class CompanyConfigMapper {
  private final CompanyConfService companyConfService;

  public CompanyConf toDomain (CompanyConfInput companyConfInput, MultipartFile imageFile)
      throws IOException {
    CompanyConf companyConfActual = companyConfService.getCompanyConf();

    // Si aucun fichier image n'est fourni, utilisez l'image de companyConfActual
    byte[] imageBytes;
    if (imageFile.isEmpty()) {
      imageBytes = Base64.getDecoder().decode(companyConfActual.getCompanyLogoBase64());
    } else {
      imageBytes = imageFile.getBytes();
    }
    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
    int CONFIG_ID = 1;

    if (companyConfInput.getCompanyName() == null || companyConfInput.getCompanyName().isEmpty()) {
      companyConfInput.setCompanyName(companyConfActual.getCompanyName());
    }
    if (companyConfInput.getCompanyDescription() == null || companyConfInput.getCompanyDescription().isEmpty()) {
      companyConfInput.setCompanyDescription(companyConfActual.getCompanyDescription());
    }
    if (companyConfInput.getCompanyPhone() == null || companyConfInput.getCompanyPhone().isEmpty()) {
      companyConfInput.setCompanyPhone(companyConfActual.getCompanyPhone());
    }
    if (companyConfInput.getCompanySlogan() == null || companyConfInput.getCompanySlogan().isEmpty()) {
      companyConfInput.setCompanySlogan(companyConfActual.getCompanySlogan());
    }
    if (companyConfInput.getNIF() == null || companyConfInput.getNIF().isEmpty()) {
      companyConfInput.setNIF(companyConfActual.getNIF());
    }
    if (companyConfInput.getSTAT() == null || companyConfInput.getSTAT().isEmpty()) {
      companyConfInput.setSTAT(companyConfActual.getSTAT());
    }
    if (companyConfInput.getRCS() == null || companyConfInput.getRCS().isEmpty()) {
      companyConfInput.setRCS(companyConfActual.getRCS());
    }
    if (companyConfInput.getCompanyEmail() == null || companyConfInput.getCompanyEmail().isEmpty()) {
      companyConfInput.setCompanyEmail(companyConfActual.getCompanyEmail());
    }
    if (companyConfInput.getCompanyAddress() == null || companyConfInput.getCompanyAddress().isEmpty()) {
      companyConfInput.setCompanyAddress(companyConfActual.getCompanyAddress());
    }



    return CompanyConf.builder()
        .id(CONFIG_ID)
        .companyName(companyConfInput.getCompanyName())
        .companyDescription(companyConfInput.getCompanyDescription())
        .companyPhone(companyConfInput.getCompanyPhone())
        .companySlogan(companyConfInput.getCompanySlogan())
        .companyLogoBase64(base64Image)
        .NIF(companyConfInput.getNIF())
        .STAT(companyConfInput.getSTAT())
        .RCS(companyConfInput.getRCS())
        .companyEmail(companyConfInput.getCompanyEmail())
        .companyAddress(companyConfInput.getCompanyAddress())
        .build();
  }
}
