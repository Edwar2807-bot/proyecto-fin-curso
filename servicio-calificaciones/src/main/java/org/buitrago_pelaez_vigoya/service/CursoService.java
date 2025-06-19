package org.buitrago_pelaez_vigoya.service;


import org.buitrago_pelaez_vigoya.dto.CursoDto;

import java.util.List;

public interface CursoService {
    CursoDto crearCurso(CursoDto cursoDto);
    List<CursoDto> obtenerTodosLosCursos();
    CursoDto obtenerCursoPorId(Long id);
    CursoDto actualizarCurso(Long id, CursoDto cursoDto);
    void eliminarCurso(Long id);
}