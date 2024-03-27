package com.HRManager.g01.entities;
import java.util.Date;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Absence {
    @Id
    private Long id;
    private Date startDate;
    private Date endDate;
    private String justification;
    @ManyToOne
    @JoinColumn(name ="idPerson",nullable = false)
    private Person person;
}