package com.proyecto.serviciolibros.servicios;

import com.proyecto.serviciolibros.modelos.Libro;
import com.proyecto.serviciolibros.modelos.Multa;
import com.proyecto.serviciolibros.modelos.Prestamo;
import com.proyecto.serviciolibros.modelos.Usuario;
import com.proyecto.serviciolibros.repositorios.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    private DataRepository repository;

    public List<Libro> getTodosLosLibros() {
        return repository.getLibros();
    }

    public List<Usuario> getTodosLosUsuarios() {
        return repository.getUsuarios();
    }

    public List<Prestamo> getLibrosPrestados() {
        return repository.getPrestamos().stream()
                .filter(p -> p.getFechaDevolucionReal() == null)
                .collect(Collectors.toList());
    }

    public List<Multa> generarReporteDeMultas() {
        return repository.getPrestamos().stream()
                .filter(p -> p.getFechaDevolucionReal() != null && p.getFechaDevolucionReal().isAfter(p.getFechaDevolucionEstimada()))
                .map(this::crearMultaDesdePrestamo)
                .collect(Collectors.toList());
    }

    private Multa crearMultaDesdePrestamo(Prestamo prestamo) {
        long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucionEstimada(), prestamo.getFechaDevolucionReal());
        double montoMulta = diasRetraso * prestamo.getUsuario().getRol().getMultaPorDia();

        return new Multa(
                prestamo.getUsuario().getNombre(),
                prestamo.getLibro().getTitulo(),
                diasRetraso,
                montoMulta
        );
    }
}
