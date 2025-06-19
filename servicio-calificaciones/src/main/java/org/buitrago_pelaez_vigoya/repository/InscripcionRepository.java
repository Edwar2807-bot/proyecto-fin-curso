package org.buitrago_pelaez_vigoya.repository;




import org.buitrago_pelaez_vigoya.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByCursoId(Long cursoId);
    List<Inscripcion> findByEstudianteId(Long estudianteId);
    Optional<Inscripcion> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}