package com.HRManager.g01.entities;
import com.HRManager.g01.security.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "position", discriminatorType = DiscriminatorType.STRING)
public class Person {

    @Id
    //SGBD  = Identity
    //par implementation = AUTO
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idPerson;
    private int soldeConges;
    protected String firstName;
    protected String lastName;
  //  @NotNull
    protected String email;
    protected String departement;
    protected String role;

    public void setSoldeConges(int soldeConges) {
        this.soldeConges = soldeConges;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //one person can have many LeaveRequest
    @OneToMany(fetch = FetchType.EAGER,mappedBy="person")
    private List<LeaveRequest> leaves;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "person")
    private List<Absence> absences;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "personne")
    private User user;



    @ManyToOne
    @JoinColumn(name = "idManager",nullable = true)
    private Manager myManager;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "RecipientID")
    private List<Bonus> bonuses;

    /*
    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departement='" + departement + '\'' +
                ", leaves=" + leaves +
                ", absences=" + absences +
                "role"+role+

                '}';
    }


     */


    public void setUser(User user) {
        this.user = user;
    }

    public void setMyManager(Manager myManager) {
        this.myManager = myManager;
    }

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String departement, String position, List<LeaveRequest> leaves, List<Absence> absences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departement = departement;
        this.leaves = leaves;
        this.absences = absences;
    }
    public Person(String firstName, String lastName, String email, String departement, String position) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departement = departement;
    }

    public Person(int soldeConges, String firstName, String lastName, String email, String departement, String role, Manager myManager) {
        this.soldeConges = soldeConges;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departement = departement;
        this.role = role;
        this.myManager = myManager;
    }
    public Person(int soldeConges, String firstName, String lastName, String email, String departement, String role) {
        this.soldeConges = soldeConges;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departement = departement;
        this.role = role;
    }

    public Person(Long idPerson, int soldeConges, String firstName, String lastName, String email, String departement, String role, List<LeaveRequest> leaves, List<Absence> absences, User user, Manager myManager) {
        this.idPerson = idPerson;
        this.soldeConges = soldeConges;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departement = departement;
        this.role = role;
        this.leaves = leaves;
        this.absences = absences;
        this.user = user;
        this.myManager = myManager;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    public void setLeaves(List<LeaveRequest> leaves) {
        this.leaves = leaves;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }
    public String getDiscriminatorValue() {
        DiscriminatorValue discriminatorValue = this.getClass().getAnnotation(DiscriminatorValue.class);
        if (discriminatorValue != null) {
            return discriminatorValue.value();
        }
        return null;
    }

    @Override
    public String toString() {
        return " firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departement='" + departement + '\'' +
                ", role='" + role + '\'' +
                ", leaves=" + leaves +
                ", absences=" + absences +
                '}';
    }

}