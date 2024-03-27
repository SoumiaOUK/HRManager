package com.HRManager.g01.services;

import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImp implements PersonService{
    @Autowired
    PersonRepository empRep ;

    @Override
    public Person savePerson(Person emp) {
        return empRep.save(emp);
    }

    @Override
    public Person updatePerson(Person emp) {
        return empRep.save(emp);
    }

    @Override
    public Person getPerson(Long id) {
        return empRep.findById(id).get();
    }

    @Override
    public List<Person> getAllPersones() {
        return empRep.findAll();
    }

    @Override
    public void deletePersonById(Long Id) {
        empRep.deleteById(Id);
    }

    @Override
    public void deleteAllPerson() {
        empRep.deleteAll();
    }
}
