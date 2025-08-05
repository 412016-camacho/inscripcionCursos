package com.example.inscripcionCursos.services.impl;

import com.example.inscripcionCursos.dtos.InscripcionDto;
import com.example.inscripcionCursos.entities.AlumnoEntity;
import com.example.inscripcionCursos.entities.CursoEntity;
import com.example.inscripcionCursos.entities.InscripcionEntity;
import com.example.inscripcionCursos.repositories.InscripcionRepository;
import com.example.inscripcionCursos.services.AlumnoService;
import com.example.inscripcionCursos.services.CursoService;
import com.example.inscripcionCursos.services.InscripcionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class InscripcionServiceImplTest {

    @MockitoBean
    private ModelMapper modelMapper;

    @MockitoSpyBean
    private InscripcionService inscripcionService;

    @MockitoBean
    private InscripcionRepository inscripcionRepository;

    @MockitoBean
    private CursoService cursoService;

    @MockitoBean
    private AlumnoService alumnoService;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        inscripcionService = new InscripcionServiceImpl(inscripcionRepository,alumnoService, cursoService, modelMapper);
    }

    @Test
    void altaInscripcionHappyPathTest() {
        AlumnoEntity alumno = new AlumnoEntity(1L, "Martin", "EEE222", "martin@mail.com", 2);
        CursoEntity curso = new CursoEntity(1L, "Matemáticas", "descripción", LocalDate.of(2025,2,2),40, 2);

        InscripcionEntity inscripcion = new InscripcionEntity(1L, alumno, curso, LocalDate.now());

        when(alumnoService.getAlumnoById(1L)).thenReturn(Optional.of(alumno));
        when(cursoService.getCursoById(1L)).thenReturn(Optional.of(curso));
        when(inscripcionRepository.findInscripcionEntitiesByAlumno_IdAndCurso_Id(1L, 1L)).thenReturn(Optional.of(inscripcion));
        when(inscripcionRepository.save(any(InscripcionEntity.class))).thenReturn(inscripcion);

        InscripcionDto result = inscripcionService.postInscripcion(modelMapper.map(inscripcion, InscripcionDto.class));

        assertNotNull(result);
        assertEquals(1L, result.getAlumnoId());
        assertEquals(1L, result.getCursoId());
        assertEquals(1L, result.getId());
    }

    @Test
    void getAllInscripcionesActivasByAlumnoTest() {
        AlumnoEntity alumno1 = new AlumnoEntity(1L, "Martin", "EEE222", "martin@mail.com", 2);
        AlumnoEntity alumno2 = new AlumnoEntity(2L, "Mario", "EES342", "mario@mail.com", 3);
        CursoEntity curso = new CursoEntity(1L, "Matemáticas", "descripción", LocalDate.of(2025,2,2),40, 2);

        InscripcionEntity inscripcion1 = new InscripcionEntity(1L, alumno1, curso, LocalDate.now());
        InscripcionEntity inscripcion2 = new InscripcionEntity(2L, alumno2, curso, LocalDate.now());
        List<InscripcionEntity> inscripciones = List.of(inscripcion1, inscripcion2);

        when(inscripcionRepository.findInscripcionEntitiesByAlumno_Id(any())).thenReturn(inscripciones);

        List<InscripcionDto> result = inscripcionService.getAllInscripcionesActivasByAlumno(1L);

        assertNotNull(result);
        assertEquals(inscripciones.size(), result.size());
    }

    @Test
    void cancelarInscripcion() {
        AlumnoEntity alumno = new AlumnoEntity(1L, "Martin", "EEE222", "martin@mail.com", 2);
        CursoEntity curso = new CursoEntity(1L, "Matemáticas", "descripción", LocalDate.of(2025,2,2),40, 2);

        InscripcionEntity inscripcion = new InscripcionEntity(1L, alumno, curso, LocalDate.now());

        when(alumnoService.getAlumnoById(1L)).thenReturn(Optional.of(alumno));
        when(cursoService.getCursoById(1L)).thenReturn(Optional.of(curso));
        when(inscripcionRepository.findInscripcionEntitiesByAlumno_IdAndCurso_Id(1L, 1L)).thenReturn(Optional.of(inscripcion));
        inscripcionService.cancelarInscripcion(1L, 1L);

        verify(inscripcionRepository , times(1)).delete(inscripcion);

        when(inscripcionRepository.findInscripcionEntitiesByAlumno_IdAndCurso_Id(1L, 1L)).thenReturn(Optional.empty());

        Optional<InscripcionEntity> result = inscripcionRepository.findInscripcionEntitiesByAlumno_IdAndCurso_Id(1L, 1L);
        assertTrue(result.isEmpty());
    }

    @Test
    void validarTest(){

    }
}