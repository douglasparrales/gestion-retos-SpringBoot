package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;


@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE users SET active = false WHERE user_id = ?")
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
    private Integer totalPoints = 0;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(nullable = false)
    private boolean active = true;
}