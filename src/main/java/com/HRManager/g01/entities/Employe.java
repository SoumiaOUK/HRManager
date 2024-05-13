package com.HRManager.g01.entities;
import jakarta.persistence.*;

import java.util.List;

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


    public Employe() {
    }

    public Employe(int soldeConges, String firstName, String lastName, String email, String departement, String role, Manager myManager) {
        super(soldeConges, firstName, lastName, email, departement, role, myManager);
    }
}