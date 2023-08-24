//package com.example.prog4.controller.Mapper;
//
//import com.example.prog4.controller.Data.InputData.EmployeeInput;
//import com.example.prog4.entity.Employee.EmployeeEntity;
//import com.example.prog4.entity.Employee.PhoneNumberEntity;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@AllArgsConstructor
//@Component
//public class PhoneNumberMapper {
//  public List<PhoneNumberEntity> toDomains (List<String> phoneNumbers, List<String> countryCodes,EmployeeEntity employeeEntity){
//    List<PhoneNumberEntity> PhoneList = new ArrayList<>();
//
//    for (int i = 0; i < phoneNumbers.size(); i++) {
//      PhoneList = toDomain(phoneNumbers.get(i), countryCodes.get(i),employeeEntity);
//    }
//  }
//
//  public PhoneNumberEntity toDomain (String phoneNumber, String countryCode, EmployeeInput employeeInput){
//    return PhoneNumberEntity.builder()
//        .phoneNumber(phoneNumber)
//        .countryCode(countryCode)
//        .employee()
//        .build();
//  }
//}
