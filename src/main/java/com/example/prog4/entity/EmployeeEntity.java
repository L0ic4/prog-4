package com.example.prog4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date birthdate;

    private String employeeNumber;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;
}
