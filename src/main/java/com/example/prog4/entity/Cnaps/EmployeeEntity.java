package com.example.prog4.entity.Cnaps;


import com.example.prog4.entity.enums.Category;
import com.example.prog4.entity.enums.Function;
import com.example.prog4.entity.enums.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cnaps_employee_entity")
public class EmployeeEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotBlank(message = "Firstname is mandatory")
  private String firstname;
  @NotBlank(message = "Lastname is mandatory")
  private String lastname;

  @NotBlank(message = "Work Email is mandatory")
  @Email(message = "Work Email must be valid")
  @Column(name = "work_email")
  private String workEmail;

  @NotBlank(message = "Personal Email is mandatory")
  @Email(message = "Personal Email must be valid")
  @Column(name = "personal_email")
  private String personalEmail;

  @NotBlank(message = "CIN is mandatory")
  private String cin;
  @NotBlank(message = "Address is mandatory")
  private String address;
  private String cnaps;
  private int children;

  @Enumerated(EnumType.STRING)
  @ColumnTransformer(read = "CAST(category AS varchar)", write = "CAST(? AS category)")
  private Category category;

  @Enumerated(EnumType.STRING)
  @ColumnTransformer(read = "CAST(sex AS varchar)", write = "CAST(? AS sex)")
  private Sex sex;

  @Enumerated(EnumType.STRING)
  @ColumnTransformer(read = "CAST(function AS varchar)", write = "CAST(? AS function)")
  private Function function;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "hire_date")
  private Date hireDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "resignation_date")
  private Date resignationDate;


}
