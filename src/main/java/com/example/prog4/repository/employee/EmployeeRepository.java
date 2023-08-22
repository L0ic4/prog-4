package com.example.prog4.repository.employee;

import com.example.prog4.entity.employee.EmployeeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Integer>,
    JpaSpecificationExecutor<EmployeeEntity> {

  EmployeeEntity findByEndToEndId(Integer endToEndId);

  List<EmployeeEntity> findAllByPhoneNumbers_CountryCode(String countryCode);
  EmployeeEntity findFirstByOrderByEmployeeNumberDesc();

  Optional<EmployeeEntity> findById(int id);

  void save(EmployeeEntity employee);
}
