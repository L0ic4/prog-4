package com.example.prog4.repository;

import com.example.prog4.entity.Employee.EmployeeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;

@org.springframework.stereotype.Repository
public interface RepositoryFacade {
  void save(EmployeeEntity employee);

  Optional<EmployeeEntity> findById(int id);

  EmployeeEntity findFirstByOrderByEmployeeNumberDesc();

  List<EmployeeEntity> findAll(Specification<EmployeeEntity> spec);
}
