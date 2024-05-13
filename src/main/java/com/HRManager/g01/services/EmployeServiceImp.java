package com.HRManager.g01.services;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.EmployeRepository;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.entities.User;
import com.HRManager.g01.security.repositories.RoleRepository;
import com.HRManager.g01.security.repositories.UserRepository;
import com.HRManager.g01.security.services.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeServiceImp implements EmployeService{
    @Autowired
    EmployeRepository empRep ;
    @Autowired
    AccountServiceImp account;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Employe saveEmploye(Employe emp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Manager manager = (Manager) userRepository.findByUsername(currentPrincipalName).getPersonne();
        emp.setMyManager(manager);
        Employe p = empRep.save(emp);
        if(p!=null){
            System.out.println("PersonService 48");
            User user = account.createUserEmp(p);
            Role roleAssigned = roleRepository.findById(p.getRole()).get();
            System.out.println("PersonServiceImp \n username= "+user.getUsername()+"\n role assigned = "+roleAssigned);
            account.addRoleToUser(user.getUsername() , roleAssigned);
            System.out.println("\n \n \n Assigne d role"+roleAssigned);
            System.out.println("P.getdiscriminatorValue"+p.getDiscriminatorValue());
        }
        return p;
    }


    @Override
    public Employe updateEmploye(Employe emp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Manager manager = (Manager) userRepository.findByUsername(currentPrincipalName).getPersonne();
        emp.setMyManager(manager);
        return empRep.save(emp);
    }

    @Override
    public Employe getEmploye(long id) {
        return empRep.findById(id).get();
    }

    @Override
    public List<Employe> getEmployeesByManager() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Manager manager =(Manager) userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("\n LEAVEREQSERVICEIMP    connected manager id"+manager.getIdPerson());
        return empRep.getEmployeesByManager(manager.getIdPerson());
    }

    @Override
    public void deleteEmployeById(Long Id) {
        empRep.deleteById(Id);
    }

    @Override
    public void deleteAllEmploye() {
        empRep.deleteAll();
    }

    @Override
    public int countEmployees(){
        return empRep.countEmployees();
    }
}
