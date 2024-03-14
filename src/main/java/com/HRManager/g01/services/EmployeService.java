package com.HRManager.g01.services;

import com.HRManager.g01.entities.Employe;
import java.util.List;
public interface EmployeService {
    Employe saveEmploye(Employe emp);
    Employe updateEmploye(Employe emp);
    Employe getEmploye(Long id);
    List<Employe> getAllEmployees();
    void deleteEmployeById(Long Id);
    void deleteAllEmploye();
}
