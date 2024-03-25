package com.HRManager.g01.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
import jakarta.persistence.Entity;

@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue
    private Long id;


    //private Employe employe;


   // private TypeConge typeConge;

    private Date dateDebut;

    private Date dateFin;

    private String statut;

    private String commentaire;

    private Boolean validationRH;

}
