package com.example.prog4.repository;

import com.example.prog4.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findFirstByOrderByEmployeeNumberDesc();
}
