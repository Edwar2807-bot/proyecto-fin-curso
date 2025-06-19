package com.proyecto.mvcweb.service.servicio_libros;

import com.proyecto.mvcweb.model.servicio_libros.LibroDTO;
import com.proyecto.mvcweb.model.servicio_libros.MultaDTO;
import com.proyecto.mvcweb.model.servicio_libros.PrestamoDTO;
import com.proyecto.mvcweb.model.servicio_libros.UsuarioDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class LibroReporteService {
    private final RestTemplate restTemplate;

    public LibroReporteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<LibroDTO> obtenerLibros() {
        return Arrays.asList(restTemplate.getForObject("http://servicio-libros:8080/api/libros/reportes/libros", LibroDTO[].class));
    }

    public List<UsuarioDTO> obtenerUsuarios() {
        return Arrays.asList(restTemplate.getForObject("http://servicio-libros:8080/api/libros/reportes/usuarios", UsuarioDTO[].class));
    }

    public List<PrestamoDTO> obtenerPrestados() {
        return Arrays.asList(restTemplate.getForObject("http://servicio-libros:8080/api/libros/reportes/prestados", PrestamoDTO[].class));
    }

    public List<MultaDTO> obtenerMultas() {
        return Arrays.asList(restTemplate.getForObject("http://servicio-libros:8080/api/libros/reportes/multas", MultaDTO[].class));
    }
}
