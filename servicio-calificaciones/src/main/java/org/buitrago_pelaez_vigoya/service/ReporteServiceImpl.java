package org.buitrago_pelaez_vigoya.service;


import org.buitrago_pelaez_vigoya.dto.CursoDto;
import org.buitrago_pelaez_vigoya.dto.EstudianteDto;
import org.buitrago_pelaez_vigoya.dto.InscripcionDto;
import org.buitrago_pelaez_vigoya.service.CursoService;
import org.buitrago_pelaez_vigoya.service.EstudianteService;
import org.buitrago_pelaez_vigoya.service.InscripcionService;
import org.buitrago_pelaez_vigoya.service.ReporteService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final CursoService cursoService;
    private final EstudianteService estudianteService;
    private final InscripcionService inscripcionService;

    @Autowired
    public ReporteServiceImpl(CursoService cursoService, EstudianteService estudianteService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
        this.inscripcionService = inscripcionService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportarCursosACsv() {
        List<CursoDto> cursos = cursoService.obtenerTodosLosCursos();
        String[] headers = {"ID", "Nombre", "Tipo de Nota"};
        ByteArrayInputStream byteArrayInputStream;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(headers))) {

            for (CursoDto curso : cursos) {
                csvPrinter.printRecord(curso.getId(), curso.getNombre(), curso.getTipoNota().name());
            }
            csvPrinter.flush();
            byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el CSV de cursos: " + e.getMessage());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cursos.csv");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @Override
    public ResponseEntity<InputStreamResource> exportarEstudiantesACsv() {
        List<EstudianteDto> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        String[] headers = {"ID", "Nombre Completo", "Código de Estudiante"};
        ByteArrayInputStream byteArrayInputStream;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(headers))) {

            for (EstudianteDto estudiante : estudiantes) {
                csvPrinter.printRecord(estudiante.getId(), estudiante.getNombreCompleto(), estudiante.getCodigoEstudiante());
            }
            csvPrinter.flush();
            byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el CSV de estudiantes: " + e.getMessage());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=estudiantes.csv");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @Override
    public ResponseEntity<InputStreamResource> exportarNotasPorCursoACsv(Long cursoId) {
        // Validar que el curso exista primero
        CursoDto curso = cursoService.obtenerCursoPorId(cursoId); // Esto lanzará ResourceNotFoundException si no existe
        List<InscripcionDto> inscripciones = inscripcionService.obtenerInscripcionesPorCurso(cursoId);
        String[] headers = {"ID Inscripción", "ID Estudiante", "Nombre Estudiante", "Nota Cuantitativa", "Nota Cualitativa"};
        ByteArrayInputStream byteArrayInputStream;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(headers))) {

            for (InscripcionDto inscripcion : inscripciones) {
                csvPrinter.printRecord(
                        inscripcion.getId(),
                        inscripcion.getEstudianteId(),
                        inscripcion.getNombreEstudiante(),
                        inscripcion.getNotaCuantitativa() != null ? inscripcion.getNotaCuantitativa().toString() : "",
                        inscripcion.getNotaCualitativa() != null ? inscripcion.getNotaCualitativa().name() : ""
                );
            }
            csvPrinter.flush();
            byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el CSV de notas para el curso " + curso.getNombre() + ": " + e.getMessage());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=notas_curso_" + curso.getNombre().replaceAll("\\s+", "_") + ".csv");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(byteArrayInputStream));
    }
}