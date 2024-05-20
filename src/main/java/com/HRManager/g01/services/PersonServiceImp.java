package com.HRManager.g01.services;
import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.PersonRepository;
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
public class PersonServiceImp implements PersonService{
    @Autowired
    PersonRepository personRep ;
    @Autowired
    AccountServiceImp account;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeServiceImp employeServiceImp;
    @Autowired
    ManagerServiceImp managerServiceImp;

    @Override
    public Person savePerson(Person person,String role) {
        Person p = null;
        if (role.equals("EMPLOYE")){

            Employe emp= new Employe(person.getSoldeConges(),person.getFirstName(),person.getLastName(),person.getEmail(),person.getDepartement(),person.getRole(),person.getMyManager());
             p=employeServiceImp.saveEmploye(emp);
            System.out.println("employee created"+p.toString());
        } else if (role.equals("MANAGER")) {
            Manager manager= new Manager(person.getSoldeConges(),person.getFirstName(),person.getLastName(),person.getEmail(),person.getDepartement(),person.getRole(),person.getMyManager());
            p=managerServiceImp.saveManager(manager);
            System.out.println("manager created"+p.toString());
        }
        if(p!=null){
            System.out.println("PersonService 48");
            User user = account.createUserEmp((Employe)person);
            Role roleAssigned = roleRepository.findById(person.getRole()).get();
            System.out.println("PersonServiceImp \n username= "+user.getUsername()+"\n role assigned = "+roleAssigned);
            account.addRoleToUser(user.getUsername() , roleAssigned);
            System.out.println("\n \n \n Assigned role"+roleAssigned);
            System.out.println("P.getdiscriminatorValue"+p.getDiscriminatorValue());
        }
        return p;
    }

    @Override
    public Person updatePerson(Person emp) {
        return personRep.save(emp);
    }

    @Override
    public Person getPerson(Long id) {
        return personRep.findById(id).get();
    }

    @Override
    public List<Person> getAllPersones() {
        return personRep.findAll();
    }

    @Override
    public void deletePersonById(Long Id) {
        personRep.deleteById(Id);
    }

    @Override
    public void deleteAllPerson() {personRep.deleteAll();}

    @Override
    public List<Employe> listEmployees(){
        return personRep.listEmployees();
    }

    @Override
    public List<Manager> listManagers(){
        return personRep.listManagers();
    }

    @Autowired
    UserRepository userRepository;
    @Override
    public List<Employe> getlistEmpByManager(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("\n LEAVEREQSERVICEIMP    connected personne id"+p.getIdPerson());

        List<Employe> listEmp = personRep.listEmpByManager(p.getIdPerson());
//        listEmp.forEach(System.out::println);
       return listEmp ;
    }
}