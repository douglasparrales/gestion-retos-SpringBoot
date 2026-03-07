package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true)
    private String username;

    private String email;
    private String password;

    @Column(name = "total_points")
    private Integer totalPoints;

    @Column(name = "registration_date")
    private LocalDate registrationDate;
}