package com.HRManager.g01.entities;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;
    private String labelle;
    private int duration;
    private String description;


    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRequest> requests;

    public LeaveType() {

    }

    @Override
    public String toString() {
        return "LeaveType{" +
                "idType=" + idType +
                ", labelle='" + labelle + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getLabelle() {
        return labelle;
    }

    public void setLabelle(String libelle) {
        this.labelle = libelle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LeaveRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<LeaveRequest> requests) {
        this.requests = requests;
    }
    //constructor
    public LeaveType(String descripion ,String labelle , int duration ){
        this.description = descripion;
        this.labelle = labelle;
        this.duration = duration;
    }
}
