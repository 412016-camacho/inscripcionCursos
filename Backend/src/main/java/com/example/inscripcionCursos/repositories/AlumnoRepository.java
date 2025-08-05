package com.example.inscripcionCursos.repositories;

import com.example.inscripcionCursos.entities.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoEntity,Long> {
    boolean existsAlumnoEntityByLegajo(String legajo);
}
