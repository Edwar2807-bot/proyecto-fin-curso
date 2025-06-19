package org.buitrago_pelaez_vigoya.repository;



import org.buitrago_pelaez_vigoya.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByCodigoEstudiante(String codigoEstudiante);
}