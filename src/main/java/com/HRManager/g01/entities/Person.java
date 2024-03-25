package com.HRManager.g01.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

    @Id
    //SGBD  = Identity
    //par implementation = AUTO
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firstName;

    protected String lastName;
    @NotNull

    protected String email;

    protected String departement;



}
