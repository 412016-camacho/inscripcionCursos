package com.example.inscripcionCursos.repositories;

import com.example.inscripcionCursos.entities.InscripcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionEntity,Long> {
    List<InscripcionEntity> findInscripcionEntitiesByAlumno_Id(Long id);

    List<InscripcionEntity> findAllByAlumno_Id(Long alumnoId);

    boolean existsInscripcionEntityByAlumno_IdAndCurso_Id(Long id, Long id1);

    Optional<InscripcionEntity> findInscripcionEntitiesByAlumno_IdAndCurso_Id(Long alumnoId, Long cursoId);
}
