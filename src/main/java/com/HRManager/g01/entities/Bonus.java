package com.HRManager.g01.entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBonus;
    private Long ammount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date awardDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idPerson",nullable = false)
    private Person RecipientID;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idTask",nullable = false)
    private Tasks task;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idManager",nullable = false)
    private Manager manager;
}
