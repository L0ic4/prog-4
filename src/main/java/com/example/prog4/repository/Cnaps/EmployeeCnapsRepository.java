package com.example.prog4.repository.Cnaps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCnapsRepository extends JpaRepository<com.example.prog4.entity.Cnaps.EmployeeEntity,Integer> {
  com.example.prog4.entity.Cnaps.EmployeeEntity getByCnaps(String cnaps);
}
