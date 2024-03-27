package com.HRManager.g01.entities;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
@Entity
public class LeaveType {
    @Id
    @GeneratedValue
    private Long idType;
    private String libelle;
    private int duration;
    private String description;
    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRequest> requests;
}
