package com.proyecto.mvcweb.service.servicio_calificaciones;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReporteService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://servicio-calificaciones:8080/api/reportes";

    public ReporteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<byte[]> descargarCursosCsv() {
        return restTemplate.exchange(BASE_URL + "/cursos/csv", HttpMethod.GET, null, byte[].class);
    }

    public ResponseEntity<byte[]> descargarEstudiantesCsv() {
        return restTemplate.exchange(BASE_URL + "/estudiantes/csv", HttpMethod.GET, null, byte[].class);
    }

    public ResponseEntity<byte[]> descargarNotasPorCurso(Long cursoId) {
        return restTemplate.exchange(BASE_URL + "/cursos/" + cursoId + "/notas/csv", HttpMethod.GET, null, byte[].class);
    }
}
