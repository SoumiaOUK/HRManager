package com.HRManager.g01.entities;

import com.HRManager.g01.enums.LeaveRequestStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    // Calculate duration based on startDate and endDate
    @PrePersist//this method will be executed if the "save"method of the repository og this entity is called
    @PreUpdate
    public void calculateDuration() {
        if (startDate != null && endDate != null && duration == 0) { // Ensure duration is not already set to avoid recursion
            long diff = endDate.getTime() - startDate.getTime();
            duration = (int) (diff / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
        }
    }
    public Long getIdLeave() {
        return idLeave;
    }

    public void setIdLeave(Long idLeave) {
        this.idLeave = idLeave;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveRequestStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveRequestStatus status) {
        this.status = status;
    }

    public Date getApprouvedDate() {
        return approuvedDate;
    }

    public void setApprouvedDate(Date approuvedDate) {
        this.approuvedDate = approuvedDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Boolean getValidationRH() {
        return validationRH;
    }

    public void setValidationRH(Boolean validationRH) {
        this.validationRH = validationRH;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Person getManager() {
        return manager;
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
                ", person=" + person +
                ", leaveType=" + leaveType +
                ", manager=" + manager +
                '}';
    }
}
