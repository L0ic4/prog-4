package com.example.prog4.service;

import com.example.prog4.entity.employee.EmployeeEntity;
import com.example.prog4.entity.employee.PhoneNumberEntity;
import com.example.prog4.repository.employee.EmployeeRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  public void save(EmployeeEntity employee, MultipartFile imageFile, List<String> phoneNumbers,
                   List<String> countryCodes) throws IOException {

    // Vérifier si les listes phoneNumbers et countryCodes ont la même taille
    if (phoneNumbers.size() != countryCodes.size()) {
      throw new IllegalArgumentException(
          "Les listes phoneNumbers et countryCodes doivent avoir la même taille.");
    }

    for (int i = 0; i < phoneNumbers.size(); i++) {
      String phoneNumberStr = phoneNumbers.get(i);
      String countryCodeStr = countryCodes.get(i);

      PhoneNumberEntity phoneNumber = new PhoneNumberEntity();
      phoneNumber.setPhoneNumber(phoneNumberStr);
      phoneNumber.setCountryCode(countryCodeStr);

      employee.addPhoneNumber(phoneNumber);
    }


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

  public Iterable<EmployeeEntity> findAll(Specification<EmployeeEntity> entitySpec) {
    return employeeRepository.findAll(entitySpec);
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
