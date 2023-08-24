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
  public void save(EmployeeEntity employeeEntity) {
    com.example.prog4.entity.Cnaps.EmployeeEntity cnapsEmployee =
        employeeCnapsRepository.getByCnaps(employeeEntity.getCnaps());
    employeeEntity.setEndToEndId(cnapsEmployee.getId());
    employeeRepository.save(employeeEntity);
  }

  @Override
  public Optional<EmployeeEntity> findById(int id) {
    Optional<EmployeeEntity> employee = employeeRepository.findById(id);

    if (employee.isPresent()) {
      Optional<com.example.prog4.entity.Cnaps.EmployeeEntity> employee1 =
          employeeCnapsRepository.findById(employee.get().getEndToEndId());

      if (employee1.isPresent()) {
        EmployeeEntity employeeEntity = employee.get();
        employeeEntity.setCnaps(employee1.get().getCnaps());
        return Optional.of(employeeEntity);
      } else {
        return Optional.empty();
      }
    } else {
      return Optional.empty();
    }
  }

  @Override
  public EmployeeEntity findFirstByOrderByEmployeeNumberDesc() {
    return employeeRepository.findFirstByOrderByEmployeeNumberDesc();
  }

  @Override
  public List<EmployeeEntity> findAll(Specification<EmployeeEntity> spec) {
    List<EmployeeEntity> employeeList = employeeRepository.findAll(spec);
    return employeeList;
  }
}
