package org.buitrago_pelaez_vigoya.service;


import org.buitrago_pelaez_vigoya.dto.InscripcionDto;
import org.buitrago_pelaez_vigoya.dto.InscripcionRequestDto;

import java.util.List;

public interface InscripcionService {
    InscripcionDto crearOActualizarInscripcion(InscripcionRequestDto inscripcionRequestDto);
    List<InscripcionDto> obtenerInscripcionesPorCurso(Long cursoId);
    List<InscripcionDto> obtenerInscripcionesPorEstudiante(Long estudianteId);
    InscripcionDto obtenerInscripcionPorId(Long id);
    void eliminarInscripcion(Long id); // Opcional, o cambiar a "darDeBaja"
}