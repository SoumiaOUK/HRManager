package com.HRManager.g01.services;

import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.PersonRepository;
import com.HRManager.g01.security.entities.User;
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

    @Override
    public Person savePerson(Person emp) {
        Person p = personRep.save(emp);
        if(p!=null){
            User user = account.createUser(p.getFirstName(),p.getLastName(),p.getEmail());
            account.addRoleToUser(user.getUsername() , p.getDiscriminatorValue());
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
