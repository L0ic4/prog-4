package com.example.prog4.service;

import com.example.prog4.entity.Employee.EmployeeEntity;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CsvService {
  public void writeEmployeesToCsv(List<EmployeeEntity> employeeEntities,
                                  HttpServletResponse response) {
    try {
      response.setContentType("text/csv");
      response.setHeader("Content-Disposition", "attachment; filename=donnees.csv");

      CSVWriter csvWriter = new CSVWriter(response.getWriter());

      // Écriture de l'en-tête du CSV
      String[] entetes =
          {"firstname", "lastname", "salary", "birthdate", "workEmail", "personalEmail",
              "CIN", "address", "hireDate", "resignationDate", "CNAPS", "position", "children",
              "sex", "category", "employeeNumber"};
      csvWriter.writeNext(entetes);

      // Écriture des données dans le CSV
      for (EmployeeEntity employee : employeeEntities) {
        String[] ligne = {employee.getFirstname(), employee.getLastname(),
            String.valueOf(employee.getSalary()),
            String.valueOf(employee.getBirthdate()),
            employee.getWorkEmail(), employee.getPersonalEmail(), employee.getCin(),
            employee.getAddress(), String.valueOf(employee.getHireDate()),
            String.valueOf(employee.getResignationDate()), employee.getCnaps(),
            employee.getFunction().toString(),
            String.valueOf(employee.getChildren()), employee.getSex().toString(),
            employee.getCategory().toString(),
            employee.getEmployeeNumber()};
        csvWriter.writeNext(ligne);
      }

      csvWriter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
