package com.example.prog4.repository.employee;

import com.example.prog4.entity.employee.CompanyConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyConfRepository extends JpaRepository<CompanyConf, Integer> {
}
