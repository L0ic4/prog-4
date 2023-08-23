package com.example.prog4.controller.Mapper;

import com.example.prog4.controller.Data.InputData.EmployeeInput;
import com.example.prog4.entity.Employee.EmployeeEntity;
import com.example.prog4.entity.Employee.PhoneNumberEntity;
import com.example.prog4.entity.enums.Category;
import com.example.prog4.entity.enums.Function;
import com.example.prog4.entity.enums.Sex;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
  public EmployeeEntity toDomain(EmployeeInput employeeInput,List<String> phoneNumbers,
                                 List<String> countryCodes) {
    EmployeeEntity employeeEntity = new EmployeeEntity();

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

    employeeEntity.setFirstname(employeeInput.getFirstname());
    employeeEntity.setLastname(employeeInput.getLastname());
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


}
