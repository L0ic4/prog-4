package com.example.prog4.entity.cnaps;

import com.example.prog4.entity.employee.PhoneNumberEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCnapsEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true, nullable = false)
  private Integer endToEndId;

  @NotBlank
  private String firstname;

  @NotBlank
  private String lastname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;
  private String workEmail;
  private String personalEmail;
  private String CIN;
  private String address;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hireDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date resignationDate;
  private String CNAPS;
  private String position;
  private int children;
  private String sex;
  private String category;
  private String employeeNumber;
  @Column(columnDefinition = "TEXT")
  private String imageBase64;
}

