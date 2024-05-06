package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {
    @Query(
            value = "select count(*) from person where role =\"EMPLOYE\";",
            nativeQuery = true)
    int countEmployees();
}
