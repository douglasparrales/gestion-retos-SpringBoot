package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    @Column(name = "puntos_totales")
    private String puntosTotales;

    @Column(name = "fecha_registro")
    private String fechaRegistro;

    @OneToMany(targetEntity = Reto.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reto> retos;

    @OneToMany(targetEntity = Inscripcion.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
}