package com.example.prog4.entity.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "company_conf")
public class CompanyConf implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "company_name")
  private String companyName;

  @Column(name = "company_description")
  private String companyDescription;

  @Column(name = "company_phone")
  private String companyPhone;

  @Column(name = "company_slogan")
  private String companySlogan;


  @Column(name = "company_logo_base64", columnDefinition = "TEXT")
  private String companyLogoBase64;

  @Column(name = "nif")
  private String NIF;

  @Column(name = "stat")
  private String STAT;

  @Column(name = "rcs")
  private String RCS;

  @Column(name = "company_email")
  private String companyEmail;

  @Column(name = "company_address")
  private String companyAddress;
}
