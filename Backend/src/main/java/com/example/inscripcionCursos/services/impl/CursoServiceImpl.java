package com.example.inscripcionCursos.services.impl;

import com.example.inscripcionCursos.dtos.CursoAltaDto;
import com.example.inscripcionCursos.entities.CursoEntity;
import com.example.inscripcionCursos.repositories.CursoRepository;
import com.example.inscripcionCursos.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    private final ModelMapper modelMapper;

    @Override
    public CursoAltaDto crearCurso(CursoAltaDto curso) {
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setDescripcion(curso.getDescripcion());
        cursoEntity.setFechaInicio(curso.getFechaInicio());
        cursoEntity.setCupoMaximo(curso.getCupoMaximo());
        cursoEntity.setAnioMinimo(curso.getAnioMinimo());

        CursoEntity saved = cursoRepository.save(cursoEntity);
        return modelMapper.map(saved, CursoAltaDto.class);
    }

    @Override
    public List<CursoAltaDto> getAllCursos() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        return modelMapper.map(cursoEntities, new TypeToken<List<CursoAltaDto>>() {}.getType());
    }

    @Override
    public Optional<CursoEntity> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }
}
