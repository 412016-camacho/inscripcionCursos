package com.example.inscripcionCursos.controllers;

import com.example.inscripcionCursos.dtos.AlumnoDto;
import com.example.inscripcionCursos.dtos.InscripcionDto;
import com.example.inscripcionCursos.services.AlumnoService;
import com.example.inscripcionCursos.services.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumnos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final InscripcionService inscripcionService;

    @PostMapping()
    public ResponseEntity<AlumnoDto> crearAlumno(@RequestBody AlumnoDto alumno) {
        return ResponseEntity.ok(alumnoService.crearAlumno(alumno));
    }

    @GetMapping("/{id}/inscripciones")
    public ResponseEntity<List<InscripcionDto>> getInscripcionesActivas(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.getAllInscripcionesActivasByAlumno(id));
    }
}
