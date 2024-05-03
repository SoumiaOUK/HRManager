package com.HRManager.g01.services;

import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.PersonRepository;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.entities.User;
import com.HRManager.g01.security.repositories.RoleRepository;
import com.HRManager.g01.security.services.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Person savePerson(Person person,String role) {

        Person p = personRep.save(person);
        if(p!=null){
            User user = account.createUser(person);
            System.out.println("jgskggsdvf'"+p.getDiscriminatorValue());
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
}
