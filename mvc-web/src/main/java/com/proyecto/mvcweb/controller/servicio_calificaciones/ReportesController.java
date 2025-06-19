package com.proyecto.mvcweb.controller.servicio_calificaciones;

import com.proyecto.mvcweb.service.servicio_calificaciones.ReporteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    private final ReporteService reporteService;

    public ReportesController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public String inicio() {
        return "reportes/index";
    }

    @GetMapping("/cursos/csv")
    public void descargarCursosCsv(HttpServletResponse response) {
        procesarDescarga(reporteService.descargarCursosCsv(), "reporte_cursos.csv", response);
    }

    @GetMapping("/estudiantes/csv")
    public void descargarEstudiantesCsv(HttpServletResponse response) {
        procesarDescarga(reporteService.descargarEstudiantesCsv(), "reporte_estudiantes.csv", response);
    }

    @GetMapping("/cursos/notas")
    public void descargarNotasPorCurso(@RequestParam Long cursoId, HttpServletResponse response) {
        procesarDescarga(reporteService.descargarNotasPorCurso(cursoId), "reporte_notas_curso_" + cursoId + ".csv", response);
    }


    private void procesarDescarga(ResponseEntity<byte[]> respuesta, String nombreArchivo, HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
            response.getOutputStream().write(respuesta.getBody());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException("Error al descargar archivo CSV", e);
        }
    }
}


