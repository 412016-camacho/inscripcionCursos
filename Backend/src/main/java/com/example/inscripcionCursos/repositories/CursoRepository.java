package com.example.inscripcionCursos.repositories;

import com.example.inscripcionCursos.entities.AlumnoEntity;
import com.example.inscripcionCursos.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity,Long> {
}
