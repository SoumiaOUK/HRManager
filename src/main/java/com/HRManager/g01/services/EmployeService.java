package com.HRManager.g01.services;

import com.HRManager.g01.entities.Employe;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmployeService {
    Employe saveEmploye(Employe emp);
    Employe updateEmploye(Employe emp);
    Employe getEmploye(long id);
    List<Employe> getEmployeesByManager();
    void deleteEmployeById(Long Id);
    void deleteAllEmploye();

    int countEmployees();
}
