package com.HRManager.g01.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employe extends Person {


    /*
    @NotNull //elle doit etre rempli
    @Size(min=3, max= 20) // nombre de carachtere
    @Min(10) // nombre min est 10
    @Max(200) // nombre max est 20
    @PastOrPresent // valeur date de passé ou présent

    attention si ces restriction ne sont pas appliqué on nous affiche une erreur sauf si on a ajouter du code dans controller



     */
    @Id
    //SGBD  = Identity
    //par implementation = AUTO
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


@Min(20)
@Max(32)
    private int soldeConges;

    private String role;

    public Employe(){
        super();
    }

    public Employe(String nom, String prenom, String email, String service, int soldeConges, String role) {
        this.firstName = nom;
        this.lastName = prenom;
        this.email = email;
        this.departement = service;
        this.soldeConges = soldeConges;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", soldeConges=" + soldeConges +
                ", role='" + role + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSoldeConges() {
        return soldeConges;
    }

    public void setSoldeConges(int soldeConges) {
        this.soldeConges = soldeConges;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
