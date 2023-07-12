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
        String matricule = generateMatricule();
        employee.setEmployeeNumber(matricule);
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
    public String generateMatricule() {
        EmployeeEntity dernierEmploye = employeeRepository.findFirstByOrderByEmployeeNumberDesc();

        if (dernierEmploye == null) {
            // Aucun matricule n'a été attribué auparavant, commencer avec EMP001
            return "EMP001";
        } else {
            String dernierMatricule = dernierEmploye.getEmployeeNumber();
            // Extraire le nombre du dernier matricule et l'incrémenter
            int dernierNumero = Integer.parseInt(dernierMatricule.substring(3));
            int nouveauNumero = dernierNumero + 1;
            // Formater le nouveau matricule avec des zéros de remplissage
            return String.format("EMP%03d", nouveauNumero);
        }
    }
}
