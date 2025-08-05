package com.example.inscripcionCursos.services;

import com.example.inscripcionCursos.dtos.AlumnoDto;
import com.example.inscripcionCursos.entities.AlumnoEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface AlumnoService {
    AlumnoDto crearAlumno(AlumnoDto alumno);
    Optional<AlumnoEntity> getAlumnoById(Long id);
}
