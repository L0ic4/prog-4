package com.example.prog4.repository;

import com.example.prog4.repository.cnaps.CnapsRepository;
import com.example.prog4.repository.employee.EmployeeRepository;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class RepositoryImpl {
  private final CnapsRepository cnapsRepository;
  private final EmployeeRepository employeeRepository;
}
