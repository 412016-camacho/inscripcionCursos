package com.example.inscripcionCursos.dtos;

import com.example.inscripcionCursos.entities.AlumnoEntity;
import com.example.inscripcionCursos.entities.CursoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDto {

    private Long id;

    @JsonProperty("alumno_id")
    private Long alumnoId;

    @JsonProperty("curso_id")
    private Long cursoId;
}
