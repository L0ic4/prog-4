package com.example.prog4.entity.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyConf {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String companyName;
  private String companyDescription;
  private String companyPhone;
  private String companySlogan;
  @Column(columnDefinition = "TEXT")
  private String companyLogoBase64;
  private String NIF;
  private String STAT;
  private String RCS;
  private String companyEmail;
  private String companyAddress;
}
