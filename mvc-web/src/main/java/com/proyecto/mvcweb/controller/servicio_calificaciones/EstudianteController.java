package com.proyecto.mvcweb.controller.servicio_calificaciones;

import com.proyecto.mvcweb.model.servicio_calificaciones.EstudianteDTO;
import com.proyecto.mvcweb.service.servicio_calificaciones.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", service.obtenerEstudiantes());
        return "estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("estudiante", new EstudianteDTO());
        return "estudiantes/formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute EstudianteDTO estudiante) {
        service.crearEstudiante(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", service.obtenerEstudiantePorId(id));
        return "estudiantes/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute EstudianteDTO estudiante) {
        service.actualizarEstudiante(id, estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminarEstudiante(id);
        return "redirect:/estudiantes";
    }
}
