package com.example.prog4.repository;

import com.example.prog4.entity.Employee.EmployeeEntity;
import com.example.prog4.repository.Cnaps.EmployeeCnapsRepository;
import com.example.prog4.repository.Employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@org.springframework.stereotype.Repository
@AllArgsConstructor
@Slf4j
public class RepositoryImpl implements Repository {
  private final EmployeeCnapsRepository employeeCnapsRepository;
  private final EmployeeRepository employeeRepository;

  @Override
  @Transactional
  public void save(EmployeeEntity employeeEntity){
    Optional<com.example.prog4.entity.Cnaps.EmployeeEntity> cnapsEmployee = employeeCnapsRepository.findById(1);

    System.out.println(cnapsEmployee.get().getCnaps());
  }

  @Override
  public Optional<EmployeeEntity> findById(int id) {
    return Optional.empty();
  }

  @Override
  public EmployeeEntity findFirstByOrderByEmployeeNumberDesc() {
    return null;
  }

  @Override
  public List<EmployeeEntity> findAll(Specification<EmployeeEntity> spec) {
    return null;
  }
}
