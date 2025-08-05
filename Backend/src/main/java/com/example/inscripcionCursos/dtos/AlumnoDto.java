package com.example.inscripcionCursos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDto {

    private String nombre;
    private String legajo;
    private String email;

    @JsonProperty("anio_cursado")
    private Integer anioCursado;
}
