package org.buitrago_pelaez_vigoya.repository;


import org.buitrago_pelaez_vigoya.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}