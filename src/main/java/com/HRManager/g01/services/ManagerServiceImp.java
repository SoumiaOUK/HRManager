package com.HRManager.g01.services;

import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImp implements ManagerService{
    @Autowired
     ManagerRepository managerRep;
    @Override
    public Manager getManagerById(long id) {
        return managerRep.getById(id);
    }
}
