package com.HRManager.g01.services;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeServiceImp implements EmployeService{
    @Autowired
    EmployeRepository empRep ;
    @Override
    public Employe saveEmploye(Employe emp) {
        return empRep.save(emp);
    }

    @Override
    public Employe updateEmploye(Employe emp) {
        return empRep.save(emp);
    }

    @Override
    public Employe getEmploye(long id) {
        return empRep.findById(id).get();
    }

    @Override
    public List<Employe> getAllEmployees() {
        return empRep.findAll();
    }

    @Override
    public void deleteEmployeById(Long Id) {
        empRep.deleteById(Id);
    }

    @Override
    public void deleteAllEmploye() {
        empRep.deleteAll();
    }
}
