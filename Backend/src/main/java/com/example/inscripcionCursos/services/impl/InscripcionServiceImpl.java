package com.example.inscripcionCursos.services.impl;

import com.example.inscripcionCursos.dtos.InscripcionDto;
import com.example.inscripcionCursos.entities.AlumnoEntity;
import com.example.inscripcionCursos.entities.CursoEntity;
import com.example.inscripcionCursos.entities.InscripcionEntity;
import com.example.inscripcionCursos.repositories.InscripcionRepository;
import com.example.inscripcionCursos.services.AlumnoService;
import com.example.inscripcionCursos.services.CursoService;
import com.example.inscripcionCursos.services.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    private final AlumnoService alumnoService;
    private final CursoService cursoService;

    private final ModelMapper modelMapper;

    @Override
    public List<InscripcionDto> getAllInscripcionesActivasByAlumno(Long id) {
        List<InscripcionEntity> inscripcionEntities = inscripcionRepository.findInscripcionEntitiesByAlumno_Id(id);
        return modelMapper.map(inscripcionEntities, new TypeToken<List<InscripcionDto>>() {}.getType());
    }

    @Override
    public InscripcionDto postInscripcion(InscripcionDto inscripcion) {

        if(!validar(inscripcion)) return null;
        Optional<AlumnoEntity> alumno = alumnoService.getAlumnoById(inscripcion.getAlumnoId());
        Optional<CursoEntity> curso = cursoService.getCursoById(inscripcion.getCursoId());
        if(alumno.isEmpty() || curso.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno o curso no encontrado");
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setAlumno(alumno.get());
        inscripcionEntity.setCurso(curso.get());
        inscripcionEntity.setFechaInscripcion(inscripcionEntity.getFechaInscripcion());

        InscripcionEntity saved = inscripcionRepository.save(inscripcionEntity);

        return modelMapper.map(saved, InscripcionDto.class);

    }

    @Override
    public Void cancelarInscripcion(Long alumnoId, Long cursoId) {
        Optional<CursoEntity> curso = cursoService.getCursoById(cursoId);
        Optional<InscripcionEntity> inscripcion = inscripcionRepository.findInscripcionEntitiesByAlumno_IdAndCurso_Id(alumnoId, cursoId);
        if(inscripcion.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscripción no encontrada");
        LocalDate fechaValida = curso.get().getFechaInicio().minusDays(7);
        if(fechaValida.isAfter(LocalDate.now())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha invalida, ya no puede cancelar la inscripción");

        inscripcionRepository.delete(inscripcion.get());

        return null;
    }

    private boolean validar(InscripcionDto inscripcion) {
        Optional<AlumnoEntity> alumno = alumnoService.getAlumnoById(inscripcion.getAlumnoId());
        Optional<CursoEntity> curso = cursoService.getCursoById(inscripcion.getCursoId());
        List<InscripcionEntity> inscripciones = inscripcionRepository.findAllByAlumno_Id(inscripcion.getAlumnoId());

        if(inscripciones.size() > 2) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puede tener más de dos inscripciones activas");
        if(alumno.get().getAnioCursado() < curso.get().getAnioMinimo()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No cumple con el año mínimo de cursado");

        int cupoMaximo = curso.get().getCupoMaximo();
        int cupos = inscripcionRepository.findInscripcionEntitiesByAlumno_Id(inscripcion.getAlumnoId()).size();
        if(cupos > cupoMaximo) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay más cupos para este curso");

        boolean existe = inscripcionRepository.existsInscripcionEntityByAlumno_IdAndCurso_Id(alumno.get().getId(), curso.get().getId());
        if(existe) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya está inscripto a este curso");

        return true;
    }
}
