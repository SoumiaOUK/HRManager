package com.HRManager.g01.security.entities;

import com.HRManager.g01.entities.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password;
    //Each user has many roles
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles= new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personne_id")
    private Person personne;

}
