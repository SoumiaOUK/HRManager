package com.HRManager.g01.entities;
import com.HRManager.g01.security.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "position", discriminatorType = DiscriminatorType.STRING)

public class Person {
    @Id
    //SGBD  = Identity
    //par implementation = AUTO
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idPerson;
    protected String firstName;
    protected String lastName;
  //  @NotNull
    protected String email;
    protected String departement;
    //one person can have many LeaveRequest
    @OneToMany(mappedBy="person")
    private List<LeaveRequest> leaves;
    @OneToMany(mappedBy = "person")
    private List<Absence> absences;

    @OneToOne(mappedBy = "personne")
    private User user;



    public Person() {
    }

    public Person(Long idPerson, String firstName, String lastName, String email, String departement, String position, List<LeaveRequest> leaves, List<Absence> absences) {
        this.idPerson = idPerson;
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

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    public List<LeaveRequest> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveRequest> leaves) {
        this.leaves = leaves;
    }

    public List<Absence> getAbsences() {
        return absences;
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

}