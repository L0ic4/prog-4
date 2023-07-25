package com.example.prog4.service;

import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.repository.EmployeeRepository;
import java.io.IOException;
import java.util.Base64;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  public void save(EmployeeEntity employee, MultipartFile imageFile) throws IOException {
    String matricule = generateMatricule();
    employee.setEmployeeNumber(matricule);
    byte[] imageBytes = imageFile.getBytes();
    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
    employee.setImageBase64(base64Image);
    employeeRepository.save(employee);
  }


  public EmployeeEntity findById(int id) {
    return employeeRepository.findById(id).orElse(null);
  }

  public Iterable<EmployeeEntity> findAll() {
    return employeeRepository.findAll();
  }

  public Iterable<EmployeeEntity> findEmployeesByFirstname(String firstname) {
    return employeeRepository.findByFirstnameContaining(firstname);
  }

  public Iterable<EmployeeEntity> findEmployeesByLastname(String lastname) {
    return employeeRepository.findByLastnameContaining(lastname);
  }

  public Iterable<EmployeeEntity> findEmployeesBySex(String sex) {
    return employeeRepository.findBySexContaining(sex);
  }


  public Iterable<EmployeeEntity> findEmployeesByHireDate(String hireDate) {
    return employeeRepository.findByHireDateContaining(hireDate);
  }

  public Iterable<EmployeeEntity> findEmployeesByResignationDate(String resignationDate) {
    return employeeRepository.findByResignationDateContaining(resignationDate);
  }

  public Iterable<EmployeeEntity> findEmployeesByPosition(String position) {
    return employeeRepository.findByPositionContaining(position);
  }


  public void update(EmployeeEntity employee) {
    employeeRepository.save(employee);
  }

  public String generateMatricule() {
    EmployeeEntity lastEmployee = employeeRepository.findFirstByOrderByEmployeeNumberDesc();

    if (lastEmployee == null) {
      // Aucun matricule n'a été attribué auparavant, commencer avec EMP001
      return "EMP001";
    } else {
      String dernierMatricule = lastEmployee.getEmployeeNumber();
      // Extraire le nombre du dernier matricule et l'incrémenter
      int dernierNumero = Integer.parseInt(dernierMatricule.substring(3));
      int nouveauNumero = dernierNumero + 1;
      // Formater le nouveau matricule avec des zéros de remplissage
      return String.format("EMP%03d", nouveauNumero);
    }
  }
}
