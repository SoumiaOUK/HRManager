package com.HRManager.g01.services;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.ManagerRepository;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.entities.User;
import com.HRManager.g01.security.repositories.RoleRepository;
import com.HRManager.g01.security.services.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImp implements ManagerService{
    @Autowired
     ManagerRepository managerRep;
    @Autowired
    AccountServiceImp account;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Manager getManagerById(long id) {
        return managerRep.getById(id);
    }

    @Override
    public List<Manager> getAllManagers(){
        return managerRep.findAll();
    }


    @Override
    public Manager saveManager(Manager manager){
        Manager m=managerRep.save(manager);
        System.out.println("manager created"+m.toString());

        if(m!=null){
            System.out.println("ManagerService 48");
            User user = account.createUserEmp(manager);
            Role roleAssigned = new Role("MANAGER");
            account.addRoleToUser(user.getUsername() , roleAssigned);
        }
        return m;}

}
