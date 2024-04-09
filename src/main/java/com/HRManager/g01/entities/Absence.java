package com.HRManager.g01.entities;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private String justification;
    @ManyToOne
    @JoinColumn(name ="idPerson",nullable = false)
    private Person person;
}