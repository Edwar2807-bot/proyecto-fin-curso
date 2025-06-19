package com.proyecto.mvcweb.controller;

import com.proyecto.mvcweb.service.servicio_libros.LibroReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibroReporteController {

    private final LibroReporteService libroService;

    public LibroReporteController(LibroReporteService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    public String verLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerLibros());
        return "servicio_libros/libros";
    }

    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", libroService.obtenerUsuarios());
        return "servicio_libros/usuarios";
    }

    @GetMapping("/prestados")
    public String verPrestados(Model model) {
        model.addAttribute("prestados", libroService.obtenerPrestados());
        return "servicio_libros/prestados";
    }

    @GetMapping("/multas")
    public String verMultas(Model model) {
        model.addAttribute("multas", libroService.obtenerMultas());
        return "servicio_libros/multas";
    }
}
