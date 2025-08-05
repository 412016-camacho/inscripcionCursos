package com.example.inscripcionCursos.services;

import com.example.inscripcionCursos.dtos.CursoAltaDto;
import com.example.inscripcionCursos.entities.CursoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CursoService {
    CursoAltaDto crearCurso(CursoAltaDto curso);

    List<CursoAltaDto> getAllCursos();

    Optional<CursoEntity> getCursoById(Long id);
}
