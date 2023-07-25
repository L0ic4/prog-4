package com.example.prog4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CompanyConf {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String companyName;
  private String companyDescription;
  private String companyPhone;
  private String companySlogan;
  private byte[] companyLogo;
  private String NIF;
  private String STAT;
  private String RCS;
  private String companyEmail;
  private String companyAddress;
}
