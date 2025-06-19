package org.buitrago_pelaez_vigoya.service;



import org.buitrago_pelaez_vigoya.dto.EstudianteDto;

import java.util.List;

public interface EstudianteService {
    EstudianteDto crearEstudiante(EstudianteDto estudianteDto);
    List<EstudianteDto> obtenerTodosLosEstudiantes();
    EstudianteDto obtenerEstudiantePorId(Long id);
    EstudianteDto actualizarEstudiante(Long id, EstudianteDto estudianteDto);
    void eliminarEstudiante(Long id);
    EstudianteDto obtenerEstudiantePorCodigo(String codigo);
}