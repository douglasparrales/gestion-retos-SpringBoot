package com.gestion_retos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_reto")
    private Long idReto;

    private String titulo;
    private String descripcion;
    private Integer puntos;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    //Foreign Key de usuario
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creador")
    private User user;

    @OneToMany(targetEntity = Inscripcion.class, fetch = FetchType.LAZY, mappedBy = "reto")
    private List<Inscripcion> inscripciones;
}
