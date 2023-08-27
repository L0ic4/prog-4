package com.example.prog4.controller.Mapper;

import com.example.prog4.controller.Data.InputData.EmployeeInput;
import com.example.prog4.entity.Employee.EmployeeEntity;
import com.example.prog4.entity.Employee.PhoneNumberEntity;
import com.example.prog4.entity.enums.Category;
import com.example.prog4.entity.enums.Function;
import com.example.prog4.entity.enums.Sex;
import com.example.prog4.repository.Employee.EmployeeRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
public class EmployeeMapper {
  private final EmployeeRepository employeeRepository;

  private static void addPhone(EmployeeEntity employeeEntity, List<String> phoneNumbers,
                               List<String> countryCodes) {

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
      employeeEntity.addPhoneNumber(phoneNumber);
    }
  }

  private static void addImage(EmployeeEntity employeeEntity, MultipartFile imageFile)
      throws IOException {
    byte[] imageBytes = imageFile.getBytes();
    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
    employeeEntity.setImageBase64(base64Image);
  }

  public EmployeeEntity toDomain(EmployeeInput employeeInput, List<String> phoneNumbers,
                                 List<String> countryCodes, MultipartFile imageFile)
      throws IOException {
    EmployeeEntity employeeEntity = new EmployeeEntity();

    addPhone(employeeEntity, phoneNumbers, countryCodes);
    addImage(employeeEntity, imageFile);

    employeeEntity.setEmployeeNumber(generateMatricule());
    employeeEntity.setFirstname(employeeInput.getFirstname().toUpperCase());
    employeeEntity.setLastname(employeeInput.getLastname());
    employeeEntity.setSalary(Double.parseDouble(employeeInput.getSalary()));
    employeeEntity.setWorkEmail(employeeInput.getWorkEmail());
    employeeEntity.setPersonalEmail(employeeInput.getPersonalEmail());
    employeeEntity.setCin(employeeInput.getCin());
    employeeEntity.setAddress(employeeInput.getAddress());
    employeeEntity.setCnaps(employeeInput.getCnaps());
    employeeEntity.setChildren(employeeInput.getChildren());
    employeeEntity.setCategory(Category.valueOf(employeeInput.getCategory()));
    employeeEntity.setSex(Sex.valueOf(employeeInput.getSex()));
    employeeEntity.setFunction(Function.valueOf(employeeInput.getFunction()));
    employeeEntity.setBirthdate(employeeInput.getBirthdate());
    employeeEntity.setHireDate(employeeInput.getHireDate());
    employeeEntity.setResignationDate(employeeInput.getResignationDate());
    return employeeEntity;
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
