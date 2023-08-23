package com.example.prog4.controller.Data.InputData;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@ToString
@AllArgsConstructor
public class EmployeeInput {
  private String firstname;
  private String lastname;
  private String workEmail;
  private String personalEmail;
  private String cin;
  private String address;
  private String cnaps;
  private int children;
  private String employeeNumber;
  private String imageBase64;
  private String category;
  private String sex;
  private String function;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hireDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date resignationDate;
}
