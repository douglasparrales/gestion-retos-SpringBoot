package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE challenge SET active = false WHERE challenge_id = ?")
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

    @Column(nullable = false)
    private boolean active = true;
}
