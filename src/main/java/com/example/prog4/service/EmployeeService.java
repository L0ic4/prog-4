package com.example.prog4.service;

import com.example.prog4.entity.Employee.EmployeeEntity;
import com.example.prog4.repository.Repository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
  private final Repository employeeRepository;

  public void save(EmployeeEntity employee) {
    employeeRepository.save(employee);
  }


  public EmployeeEntity findById(int id) {
    return employeeRepository.findById(id).orElse(null);
  }

  public Iterable<EmployeeEntity> findAll(Specification<EmployeeEntity> entitySpec) {
    return employeeRepository.findAll(entitySpec);
  }

  public Iterable<EmployeeEntity> findAllWithoutCnaps(Specification<EmployeeEntity> entitySpec) {
    return employeeRepository.findAllWithoutCnaps(entitySpec);
  }

}
