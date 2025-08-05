package com.example.inscripcionCursos.services.impl;

import com.example.inscripcionCursos.dtos.AlumnoDto;
import com.example.inscripcionCursos.entities.AlumnoEntity;
import com.example.inscripcionCursos.repositories.AlumnoRepository;
import com.example.inscripcionCursos.services.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    private final ModelMapper modelMapper;

    @Override
    public AlumnoDto crearAlumno(AlumnoDto alumno) {
        if(alumno.getAnioCursado() < 1 || alumno.getAnioCursado() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El año de cursado debe estar entre 1 y 5");
        }
        if(alumnoRepository.existsAlumnoEntityByLegajo(alumno.getLegajo())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El legajo ya existe");
        }

        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity.setNombre(alumno.getNombre());
        alumnoEntity.setLegajo(alumno.getLegajo());
        alumnoEntity.setEmail(alumno.getEmail());
        alumnoEntity.setAnioCursado(alumno.getAnioCursado());

        AlumnoEntity saved = alumnoRepository.save(alumnoEntity);

        return modelMapper.map(saved, new TypeToken<AlumnoDto>(){}.getType());

    }

    @Override
    public Optional<AlumnoEntity> getAlumnoById(Long id) {
        return alumnoRepository.findById(id);
    }


}
