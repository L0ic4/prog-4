package com.example.prog4.service;

import com.example.prog4.entity.employee.EmployeeEntity;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CsvFileGenerator {
  public void writeEmployeesToCsv(List<EmployeeEntity> employeeEntities,
                                  HttpServletResponse response) {
    try {
      response.setContentType("text/csv");
      response.setHeader("Content-Disposition", "attachment; filename=donnees.csv");

      CSVWriter csvWriter = new CSVWriter(response.getWriter());

      // Écriture de l'en-tête du CSV
      String[] entetes =
          {"firstname", "lastname", "birthdate", "phoneNumbers", "workEmail", "personalEmail",
              "CIN", "address", "hireDate", "resignationDate", "CNAPS", "position", "children",
              "sex", "category", "employeeNumber"};
      csvWriter.writeNext(entetes);

      // Écriture des données dans le CSV
      for (EmployeeEntity employee : employeeEntities) {
        String[] ligne = {employee.getFirstname(), employee.getLastname(),
            String.valueOf(employee.getBirthdate()), employee.getPhoneNumbers().toString(),
            employee.getWorkEmail(), employee.getPersonalEmail(), employee.getCIN(),
            employee.getAddress(), String.valueOf(employee.getHireDate()),
            String.valueOf(employee.getResignationDate()), employee.getCNAPS(),
            employee.getPosition(),
            String.valueOf(employee.getChildren()), employee.getSex(), employee.getCategory(),
            employee.getEmployeeNumber()};
        csvWriter.writeNext(ligne);
      }

      csvWriter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
