package com.proyecto.serviciolibros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.serviciolibros.modelos.Libro;
import com.proyecto.serviciolibros.modelos.Prestamo;
import com.proyecto.serviciolibros.modelos.PrestamoDTO;
import com.proyecto.serviciolibros.modelos.Rol;
import com.proyecto.serviciolibros.modelos.Usuario;
import com.proyecto.serviciolibros.repositorios.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Biblioteca implements CommandLineRunner {

    @Autowired
    private DataRepository repository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Usuarios
        TypeReference<List<Usuario>> typeReferenceUsuarios = new TypeReference<>() {};
        InputStream inputStreamUsuarios = new ClassPathResource("data/usuarios.json").getInputStream();
        List<Usuario> usuarios = mapper.readValue(inputStreamUsuarios, typeReferenceUsuarios);
        repository.getUsuarios().addAll(usuarios);

        // Libros
        TypeReference<List<Libro>> typeReferenceLibros = new TypeReference<>() {};
        InputStream inputStreamLibros = new ClassPathResource("data/libros.json").getInputStream();
        List<Libro> libros = mapper.readValue(inputStreamLibros, typeReferenceLibros);
        repository.getLibros().addAll(libros);

        // Préstamos
        TypeReference<List<PrestamoDTO>> typeReferencePrestamos = new TypeReference<>() {};
        InputStream inputStreamPrestamos = new ClassPathResource("data/prestamos.json").getInputStream();
        List<PrestamoDTO> prestamosDTO = mapper.readValue(inputStreamPrestamos, typeReferencePrestamos);

        Map<Long, Usuario> mapaUsuarios = usuarios.stream().collect(Collectors.toMap(Usuario::getId, Function.identity()));
        Map<Long, Libro> mapaLibros = libros.stream().collect(Collectors.toMap(Libro::getId, Function.identity()));

        for (PrestamoDTO dto : prestamosDTO) {
            Usuario usuario = mapaUsuarios.get(dto.usuarioId);
            Libro libro = mapaLibros.get(dto.libroId);

            if (usuario != null && libro != null) {
                LocalDate fechaPrestamo = LocalDate.now().minusDays(dto.diasDesdePrestamo);
                LocalDate fechaDevolucionEstimada = fechaPrestamo.plusDays(dto.diasParaDevolucion);
                LocalDate fechaDevolucionReal = (dto.diasDesdeDevolucion != null) ? LocalDate.now().minusDays(dto.diasDesdeDevolucion) : null;

                Prestamo prestamo = new Prestamo(dto.id, usuario, libro, fechaPrestamo, fechaDevolucionEstimada, fechaDevolucionReal);
                repository.getPrestamos().add(prestamo);
            }
        }
    }
}
