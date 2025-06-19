package org.buitrago_pelaez_vigoya.service;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface ReporteService {
    ResponseEntity<InputStreamResource> exportarCursosACsv();
    ResponseEntity<InputStreamResource> exportarEstudiantesACsv();
    ResponseEntity<InputStreamResource> exportarNotasPorCursoACsv(Long cursoId);
}