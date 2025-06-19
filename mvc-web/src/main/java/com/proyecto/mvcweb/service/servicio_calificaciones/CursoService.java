package com.proyecto.mvcweb.service.servicio_calificaciones;

import com.proyecto.mvcweb.model.servicio_calificaciones.CursoDTO;
import com.proyecto.mvcweb.model.servicio_libros.LibroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CursoService {

    private final String BASE_URL = "http://servicio-calificaciones:8080/api/cursos";

    @Autowired
    private RestTemplate restTemplate;

    public List<CursoDTO> obtenerCursos() {
        CursoDTO[] lista = restTemplate.getForObject(BASE_URL, CursoDTO[].class);
        return Arrays.asList(lista);
    }

    public CursoDTO obtenerCursoPorId(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, CursoDTO.class);
    }

    public void crearCurso(CursoDTO curso) {
        restTemplate.postForObject(BASE_URL, curso, CursoDTO.class);
    }

    public void actualizarCurso(Long id, CursoDTO curso) {
        restTemplate.put(BASE_URL + "/" + id, curso);
    }

    public void eliminarCurso(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
