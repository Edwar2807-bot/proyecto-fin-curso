package com.proyecto.mvcweb.controller.servicio_calificaciones;


import com.proyecto.mvcweb.service.servicio_calificaciones.InscripcionService;
import com.proyecto.mvcweb.service.servicio_calificaciones.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    private final ReporteService service;

    public ReportesController(ReporteService service) {
        this.service = service;
    }
    @GetMapping("/cursos/csv")
    public ResponseEntity<InputStreamResource> exportarCursosCSV() {
        return (ResponseEntity<InputStreamResource>) service.descargarReporteCursos();
    }

    @GetMapping("/estudiantes/csv")
    public ResponseEntity<InputStreamResource> exportarEstudiantesCSV() {
        return (ResponseEntity<InputStreamResource>) service.descargarReporteEstudiantes();
    }

    @GetMapping("/cursos/{cursoId}/notas/csv")
    public ResponseEntity<InputStreamResource> exportarNotasCursoCSV(@PathVariable Long cursoId) {
        return (ResponseEntity<InputStreamResource>) service.descargarReporteNotasPorCurso(cursoId);
    }
}
