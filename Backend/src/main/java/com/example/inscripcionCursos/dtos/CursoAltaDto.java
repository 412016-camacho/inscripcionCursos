package com.example.inscripcionCursos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoAltaDto {

    private String nombre;
    private String descripcion;

    @JsonProperty("fecha_inicio")
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @JsonProperty("cupo_maximo")
    private Integer cupoMaximo;

    @JsonProperty("anio_minimo")
    private Integer anioMinimo;
}
