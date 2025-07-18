package com.proyecto.mvcweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("mensaje", "¡Bienvenido al sistema!");
        return "index"; // Busca templates/index_calculos.html
    }

    @GetMapping("/calificaciones")
    public String inicioCalificaciones() {
        return "index_calificaciones"; // sin .html
    }

    @GetMapping("/calculos")
    public String  inicioCalculos(){
        return "index_calculos";
    }

    @GetMapping("/book")
    public String  inicioLibros(){
        return "index_libros";
    }


}
