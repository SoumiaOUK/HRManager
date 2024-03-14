package com.HRManager.g01.entities;

import java.util.Date;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Absence {
    @Id
    private Long id;


    //private Employe employe;

    private String motif;

    private Date dateDebut;

    private Date dateFin;
}
