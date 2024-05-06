package com.HRManager.g01.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
@DiscriminatorValue("MANAGER")

public class Manager extends Person{


    @OneToMany(mappedBy = "idLeave")
    private List<LeaveRequest> leaves;

    @OneToMany(mappedBy = "idPerson",fetch = FetchType.EAGER)
    private List<Person> personnelle;
   /* public Manager(Long idPerson, String firstName, String lastName, String email, String departement, String position, List<LeaveRequest> leaves, List<Absence> absences, List<LeaveRequest> leaves1) {
        super(idPerson, firstName, lastName, email, departement, position, leaves, absences);
        this.leaves = leaves1;
    }

    */

    public Manager() {

    }

    public Manager(int soldeConges, String firstName, String lastName, String email, String departement, String role, Manager myManager) {
        super(soldeConges, firstName, lastName, email, departement, role, myManager);
    }

    @Override
    public List<LeaveRequest> getLeaves() {
        return leaves;
    }



    public List<Person> getPersonnelle() {
        return personnelle;
    }

    public void setPersonnelle(List<Person> personnelle) {
        this.personnelle = personnelle;
    }

    @Override
    public void setLeaves(List<LeaveRequest> leaves) {
        this.leaves = leaves;
    }

    @Override
    public String toString() {
        return "Manager{" +
                ", idPerson=" + idPerson +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }
}
