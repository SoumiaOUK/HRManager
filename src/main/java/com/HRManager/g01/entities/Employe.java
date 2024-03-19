package com.HRManager.g01.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employe {


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

    private String nom;

    private String prenom;
@NotNull

    private String email;

    private String service;
@Min(20)
@Max(32)
    private int soldeConges;

    private String role;

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", service='" + service + '\'' +
                ", soldeConges=" + soldeConges +
                ", role='" + role + '\'' +
                '}';
    }

    public Employe(){
        super();
    }

    public Employe(String nom, String prenom, String email, String service, int soldeConges, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.service = service;
        this.soldeConges = soldeConges;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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
