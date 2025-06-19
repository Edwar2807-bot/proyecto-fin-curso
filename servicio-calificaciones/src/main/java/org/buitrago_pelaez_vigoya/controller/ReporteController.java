package org.buitrago_pelaez_vigoya.controller;


import org.buitrago_pelaez_vigoya.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/cursos/csv")
    public ResponseEntity<InputStreamResource> exportarCursos() {
        return reporteService.exportarCursosACsv();
    }

    @GetMapping("/estudiantes/csv")
    public ResponseEntity<InputStreamResource> exportarEstudiantes() {
        return reporteService.exportarEstudiantesACsv();
    }

    @GetMapping("/cursos/{cursoId}/notas/csv")
    public ResponseEntity<InputStreamResource> exportarNotasPorCurso(@PathVariable Long cursoId) {
        return reporteService.exportarNotasPorCursoACsv(cursoId);
    }
}