package com.HRManager.g01.entities;
import jakarta.persistence.*;
@Entity
@DiscriminatorValue("EMPLOYE")
public class Employe extends Person {
    /*
    @NotNull //elle doit etre rempli
    @Size(min=3, max= 20) // nombre de carachtere
    @Min(10) // nombre min est 10
    @Max(200) // nombre max est 20
    @PastOrPresent // valeur date de passé ou présent
    attention si ces restriction ne sont pas appliqué on nous affiche une erreur sauf si on a ajouter du code dans controller
     */
    private int soldeConges;
    private String role;
    public Employe(){
        super();
    }
    public Employe(Long id, int soldeConges, String role) {
        this.soldeConges = soldeConges;
        this.role = role;
    }



    @Override
    public String toString() {
        return "Employe{" +
                "soldeConges=" + soldeConges +
                ", role='" + role + '\'' +
                ", idPerson=" + idPerson +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departement='" + departement + '\'' +
                '}';
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