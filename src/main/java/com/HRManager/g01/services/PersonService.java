package com.HRManager.g01.services;

import com.HRManager.g01.entities.Person;
import java.util.List;
public interface PersonService {
    Person savePerson(Person emp,String role);
    Person updatePerson(Person emp);
    Person getPerson(Long id);
    List<Person> getAllPersones();
    void deletePersonById(Long Id);
    void deleteAllPerson();
}
