package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    @Query(
            value = "select * from person WHERE position=\"EMPLOYE\";",
            nativeQuery = true)
    List<Employe> listEmployees();


    @Query(
            value = "select * from person WHERE position=\"MANAGER\";",
            nativeQuery = true)
    List<Manager> listManagers();
}
