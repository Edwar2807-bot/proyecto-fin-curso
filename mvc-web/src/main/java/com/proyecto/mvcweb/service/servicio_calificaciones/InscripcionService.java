package com.proyecto.mvcweb.service.servicio_calificaciones;


import com.proyecto.mvcweb.model.servicio_calificaciones.InscripcionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class InscripcionService {

    private final String BASE_URL = "http://servicio-calificaciones:8080/api/inscripciones";

    @Autowired
    private RestTemplate restTemplate;

    public List<InscripcionDTO> obtenerInscripcionesPorCurso(Long cursoId) {
        InscripcionDTO[] lista = restTemplate.getForObject(BASE_URL + "/curso/" + cursoId, InscripcionDTO[].class);
        return Arrays.asList(lista);
    }

    public List<InscripcionDTO> obtenerInscripcionesPorEstudiante(Long estudianteId) {
        InscripcionDTO[] lista = restTemplate.getForObject(BASE_URL + "/estudiante/" + estudianteId, InscripcionDTO[].class);
        return Arrays.asList(lista);
    }

    public InscripcionDTO obtenerPorId(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, InscripcionDTO.class);
    }

    public void crearInscripcion(InscripcionDTO inscripcion) {
        restTemplate.postForObject(BASE_URL, inscripcion, InscripcionDTO.class);
    }

    public void eliminarInscripcion(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
