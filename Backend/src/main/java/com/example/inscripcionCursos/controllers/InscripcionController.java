package com.example.inscripcionCursos.controllers;

import com.example.inscripcionCursos.dtos.InscripcionDto;
import com.example.inscripcionCursos.services.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inscripciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping()
    public ResponseEntity<InscripcionDto> altaInscripcion(@RequestBody InscripcionDto inscripcion) {
        return ResponseEntity.ok(inscripcionService.postInscripcion(inscripcion));
    }

    @DeleteMapping()
    public ResponseEntity<Void> cancelarInscripcion(@RequestParam Long alumnoId, @RequestParam Long cursoId) {
        return ResponseEntity.ok(inscripcionService.cancelarInscripcion(alumnoId, cursoId));
    }
}
