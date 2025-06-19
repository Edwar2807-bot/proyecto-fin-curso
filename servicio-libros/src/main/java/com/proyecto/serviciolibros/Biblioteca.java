package com.proyecto.serviciolibros;

import com.proyecto.serviciolibros.modelos.Libro;
import com.proyecto.serviciolibros.modelos.Prestamo;
import com.proyecto.serviciolibros.modelos.Rol;
import com.proyecto.serviciolibros.modelos.Usuario;
import com.proyecto.serviciolibros.repositorios.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Biblioteca implements CommandLineRunner {

    @Autowired
    private DataRepository repository;

    @Override
    public void run(String... args) throws Exception {

        // Empleados
        Usuario estudiante = new Usuario(1L, "Rey Ruiz", Rol.ESTUDIANTE);
        Usuario docente = new Usuario(2L, "Carlos Torres", Rol.DOCENTE);
        Usuario empleado = new Usuario(3L, "Luis Pérez", Rol.EMPLEADO);
        Usuario empleado2 = new Usuario(4L, "Omar Gonzalez", Rol.EMPLEADO);
        repository.getUsuarios().add(estudiante);
        repository.getUsuarios().add(docente);
        repository.getUsuarios().add(empleado);
        repository.getUsuarios().add(empleado2);

        // Libros
        Libro libro1 = new Libro(101L, "100 años de soledad", "Gabriel Garcia Marquez");
        Libro libro2 = new Libro(102L, "Don Quijote de la Mancha", "Miguel de Cervantes Saavedra");
        Libro libro3 = new Libro(103L, "Clean Code", "Robert C. Martin");
        Libro libro4 = new Libro(104L, "El principito", "Antoine de Saint-Exupéry");

        repository.getLibros().add(libro1);
        repository.getLibros().add(libro2);
        repository.getLibros().add(libro3);
        repository.getLibros().add(libro4);

        // Préstamo 1: Entregado con retraso (genera multa) - Estudiante
        repository.getPrestamos().add(new Prestamo(1L, estudiante, libro1,
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(5)
        ));

        // Préstamo 2: Entregado a tiempo (no genera multa)
        repository.getPrestamos().add(new Prestamo(2L, docente, libro2,
                LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(2)
        ));

        // Préstamo 3: Aún no devuelto (considerado "libro prestado")
        repository.getPrestamos().add(new Prestamo(3L, empleado, libro3,
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                null
        ));

        // Préstamo 4: Entregado con retraso (genera multa) - Empleado
        repository.getPrestamos().add(new Prestamo(4L, empleado2, libro4,
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(12),
                LocalDate.now().minusDays(5)
        ));
    }
}
