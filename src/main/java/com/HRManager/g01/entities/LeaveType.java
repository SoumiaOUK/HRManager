package com.HRManager.g01.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class LeaveType {
    @Id
    @GeneratedValue
    private Long id;

    private String libelle;

    private int dureeParDefaut;

    private String couleur;
}
