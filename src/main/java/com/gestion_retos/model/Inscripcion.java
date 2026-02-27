package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_inscripcion")
    private Long idInscripcion;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    private boolean completado;

    //Foreign Key de usuario
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "usuario")
    private User user;

    //Foreign Key de reto
    @ManyToOne(targetEntity = Reto.class)
    @JoinColumn(name = "reto")
    private Reto reto;
}
