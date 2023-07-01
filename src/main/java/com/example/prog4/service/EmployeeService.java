package com.example.prog4.service;

import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void save(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeEntity findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Iterable<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    public void update(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }
}
