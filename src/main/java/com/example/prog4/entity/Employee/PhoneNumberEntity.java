package com.example.prog4.entity.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phone_number_entity")
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "UniquePhoneNumbers", columnNames = {"phone_number", "country_code"})})
public class PhoneNumberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "Country code is required")
//  @Pattern(regexp= "^[+][0-9]{1,3}$",message = "Invalid country code")
  @Column(name = "country_code")
  private String countryCode;

  @NotBlank(message = "Phone number is required")
//  @Pattern(regexp = "^[0-9]{9,15}$", message = "Invalid phone number")
  @Column(name = "phone_number")
  private String phoneNumber;

  @ManyToOne
  private EmployeeEntity employee;
}
