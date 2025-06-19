package com.proyecto.mvcweb.service.servicio_calificaciones;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;

@Service
public class ReporteService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://servicio-calificaciones:8080/api/reportes";

    public ReporteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Resource descargarReporteCursos() {
        return descargarArchivoCSV(baseUrl + "/cursos/csv");
    }

    public Resource descargarReporteEstudiantes() {
        return descargarArchivoCSV(baseUrl + "/estudiantes/csv");
    }

    public Resource descargarReporteNotasPorCurso(Long cursoId) {
        return descargarArchivoCSV(baseUrl + "/cursos/" + cursoId + "/notas/csv");
    }

    private Resource descargarArchivoCSV(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(MediaType.parseMediaTypes("text/csv"));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                byte[].class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return new ByteArrayResource(response.getBody());
        }

        return null;
    }
}

