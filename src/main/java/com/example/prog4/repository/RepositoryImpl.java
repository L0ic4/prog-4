package com.example.prog4.repository;

import com.example.prog4.entity.Employee.EmployeeEntity;
import com.example.prog4.repository.Cnaps.EmployeeCnapsRepository;
import com.example.prog4.repository.Employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
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
      LocalDate birthdate = employee.get().getBirthdate();
      Integer age = ageCalculator(birthdate);
      employee.get().setAge(age);
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
  public List<EmployeeEntity> findAllWithoutCnaps(Specification<EmployeeEntity> spec) {
    return employeeRepository.findAll(spec);
  }

  @Override
  public List<EmployeeEntity> findAll(Specification<EmployeeEntity> spec) {
    // Utilisez le repository approprié pour récupérer les entités EmployeeEntity
    List<EmployeeEntity> employees = employeeRepository.findAll(spec);

    // Pour chaque entité EmployeeEntity, récupérez l'entité EmployeeCnapsEntity correspondante
    for (EmployeeEntity employee : employees) {
      int endToEndId = employee.getEndToEndId();

      // Utilisez l'endToEndId pour récupérer l'EmployeeCnapsEntity correspondante
      com.example.prog4.entity.Cnaps.EmployeeEntity
          cnapsEntity = employeeCnapsRepository.findById(endToEndId).orElse(null);

      if (cnapsEntity != null) {
        // Récupérez la valeur de la colonne "cnaps" de l'entité EmployeeCnapsEntity
        String cnapsValue = cnapsEntity.getCnaps();

        // Mettez à jour l'entité EmployeeEntity avec la valeur de la colonne "cnaps"
        employee.setCnaps(cnapsValue);
      }
    }

    return employees;
  }

  public int ageCalculator(LocalDate birthdate) {
    LocalDate actualDate = LocalDate.now();
    Period periodDifference = Period.between(birthdate, actualDate);
    return periodDifference.getYears();
  }
}
