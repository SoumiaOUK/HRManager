package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.Absence;
import com.HRManager.g01.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
