package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "inscription_id")
    private Long inscriptionId;

    @Column(name = "inscription_date")
    private LocalDate inscriptionDate;

    private boolean completed = false;

    //Foreign Key for user
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    //Foreign Key for challenge
    @ManyToOne(targetEntity = Challenge.class)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
