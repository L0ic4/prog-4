package com.example.prog4.controller.Data.InputData;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class EmployeeInput {
  private String firstname;
  private String lastname;
  private String salary;
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
  private LocalDate birthdate;
  private LocalDate hireDate;
  private LocalDate resignationDate;
}
