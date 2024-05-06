package com.HRManager.g01.entities;

import com.HRManager.g01.enums.LeaveRequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Data
@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeave;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")

    private Date endDate;

    private int duration;
    private String reason;
    @Enumerated(EnumType.STRING)
    private LeaveRequestStatus status;
    @Temporal(TemporalType.DATE)

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


    @OneToOne
    @JoinColumn(name = "leaveProof_id", referencedColumnName = "idLeaveProof")
    private LeaveProof leaveProof;

    public LeaveRequest() {
    }

    public LeaveRequest(Date startDate, Date endDate, String reason, String commentaire) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.commentaire = commentaire;
    }

    @PrePersist//this method will be executed if the "save"method of the repository og this entity is called
    @PreUpdate
    public void calculateDuration() {
        if (startDate != null && endDate != null && duration == 0) { // Ensure duration is not already set to avoid recursion
            long diff = endDate.getTime() - startDate.getTime();
            duration = (int) (diff / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
        }

    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(LeaveRequestStatus status) {
        this.status = status;
    }

    public void setApprouvedDate(Date approuvedDate) {
        this.approuvedDate = approuvedDate;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setValidationRH(Boolean validationRH) {
        this.validationRH = validationRH;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "idLeave=" + idLeave +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                ", approuvedDate=" + approuvedDate +
                ", signature='" + signature + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", validationRH=" + validationRH +
                ", person=" + person.getFirstName() +
                ", leaveType=" + leaveType +
                ", manager=" + manager +
                '}';
    }
}
