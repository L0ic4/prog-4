package com.example.prog4.controller.Data.InputData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class CompanyConfInput {
  private String companyName;
  private String companyDescription;
  private String companyPhone;
  private String companySlogan;
  private String NIF;
  private String STAT;
  private String RCS;
  private String companyEmail;
  private String companyAddress;
}
