package com.HRManager.g01.entities;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder

public class LeaveProof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeaveProof;

    private String pathToProof;

    @OneToOne(mappedBy = "leaveProof")
    private LeaveRequest leaveRequest;

    public LeaveProof(String pathToProof, LeaveRequest leaveRequest) {
        this.pathToProof = pathToProof;
        this.leaveRequest = leaveRequest;
    }
    public LeaveProof(Long idLeaveProof, String pathToProof, LeaveRequest leaveRequest) {
        this.idLeaveProof = idLeaveProof;
        this.pathToProof = pathToProof;
        this.leaveRequest = leaveRequest;
    }
}
