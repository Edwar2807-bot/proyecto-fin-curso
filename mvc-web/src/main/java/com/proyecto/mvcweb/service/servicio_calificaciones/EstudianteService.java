package com.proyecto.mvcweb.service.servicio_calificaciones;

import com.proyecto.mvcweb.model.servicio_calificaciones.EstudianteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EstudianteService {

    private final String BASE_URL = "http://servicio-calificaciones:8081/api/estudiantes";

    @Autowired
    private RestTemplate restTemplate;

    public List<EstudianteDTO> obtenerEstudiantes() {
        EstudianteDTO[] lista = restTemplate.getForObject(BASE_URL, EstudianteDTO[].class);
        return Arrays.asList(lista);
    }

    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, EstudianteDTO.class);
    }

    public EstudianteDTO obtenerPorCodigo(String codigo) {
        return restTemplate.getForObject(BASE_URL + "/codigo/" + codigo, EstudianteDTO.class);
    }

    public void crearEstudiante(EstudianteDTO estudiante) {
        restTemplate.postForObject(BASE_URL, estudiante, EstudianteDTO.class);
    }

    public void actualizarEstudiante(Long id, EstudianteDTO estudiante) {
        restTemplate.put(BASE_URL + "/" + id, estudiante);
    }

    public void eliminarEstudiante(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
