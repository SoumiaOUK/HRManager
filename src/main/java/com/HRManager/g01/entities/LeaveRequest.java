package com.HRManager.g01.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue
    private Long idLeave;
    private Date startDate;
    private Date endDate;
    private int duration;
    private String reason;
    private String status;
    private Date approuvedDate;
    private String signature;
    private String commentaire;
    private Boolean validationRH;
    @ManyToOne
    @JoinColumn(name = "idPerson",nullable = false)
    private Person person;
    @ManyToOne
    @JoinColumn(name = "idType",nullable = false)
    private LeaveType leaveType;
    @ManyToOne // Corrected relationship
    @JoinColumn(name = "idManager", nullable = false)
    private Person manager; // Modified to reference the superclass Person
}
