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
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "challenge_id")
    private Long challengeId;

    private String title;
    private String description;
    private Integer points;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    //Foreign Key for User.class
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator")
    private User user;
}
