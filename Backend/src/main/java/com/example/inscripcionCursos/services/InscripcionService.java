package com.example.inscripcionCursos.services;

import com.example.inscripcionCursos.dtos.InscripcionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InscripcionService {
    List<InscripcionDto> getAllInscripcionesActivasByAlumno(Long id);

    InscripcionDto postInscripcion(InscripcionDto inscripcion);

    Void cancelarInscripcion(Long alumnoId, Long cursoId);
}
