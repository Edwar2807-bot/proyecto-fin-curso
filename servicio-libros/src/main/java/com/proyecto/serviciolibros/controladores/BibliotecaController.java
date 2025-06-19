package com.proyecto.serviciolibros.controladores;

import com.proyecto.serviciolibros.modelos.Libro;
import com.proyecto.serviciolibros.modelos.Multa;
import com.proyecto.serviciolibros.modelos.Prestamo;
import com.proyecto.serviciolibros.modelos.Usuario;
import com.proyecto.serviciolibros.servicios.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/libros/reportes")
public class BibliotecaController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/libros")
    public List<Libro> obtenerReporteLibros() {
        return reporteService.getTodosLosLibros();
    }

    @GetMapping("/usuarios")
    public List<Usuario> obtenerReporteUsuarios() {
        return reporteService.getTodosLosUsuarios();
    }

    @GetMapping("/prestados")
    public List<Prestamo> obtenerReportePrestados() {
        return reporteService.getLibrosPrestados();
    }

    @GetMapping("/multas")
    public List<Multa> obtenerReporteMultas() {
        return reporteService.generarReporteDeMultas();
    }
}
