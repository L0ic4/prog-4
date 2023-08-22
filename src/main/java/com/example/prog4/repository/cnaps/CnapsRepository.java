package com.example.prog4.repository.cnaps;

import com.example.prog4.entity.cnaps.EmployeeCnapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CnapsRepository extends JpaRepository<EmployeeCnapsEntity, Integer> {
  EmployeeCnapsEntity getByCNAPS(String cnaps);
}
