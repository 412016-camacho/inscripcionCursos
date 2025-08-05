package com.example.inscripcionCursos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AlumnoEntity alumno;

    @ManyToOne
    private CursoEntity curso;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;
}
