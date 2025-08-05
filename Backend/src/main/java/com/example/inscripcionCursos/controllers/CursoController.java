package com.example.inscripcionCursos.controllers;

import com.example.inscripcionCursos.dtos.CursoAltaDto;
import com.example.inscripcionCursos.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping()
    public ResponseEntity<CursoAltaDto> crearAlumno(@RequestBody CursoAltaDto curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }

    @GetMapping()
    public ResponseEntity<List<CursoAltaDto>> getAllCursos() {
        return ResponseEntity.ok(cursoService.getAllCursos());
    }
}
