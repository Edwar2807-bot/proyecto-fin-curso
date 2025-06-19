package com.proyecto.mvcweb.controller.servicio_calificaciones;

import com.proyecto.mvcweb.model.servicio_calificaciones.CursoDTO;
import com.proyecto.mvcweb.service.servicio_calificaciones.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", service.obtenerCursos());
        return "cursos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCursoForm(Model model) {
        model.addAttribute("curso", new CursoDTO());
        return "cursos/formulario";
    }

    @PostMapping
    public String guardarCurso(@ModelAttribute CursoDTO curso) {
        service.crearCurso(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        model.addAttribute("curso", service.obtenerCursoPorId(id));
        return "cursos/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCurso(@PathVariable Long id, @ModelAttribute CursoDTO curso) {
        service.actualizarCurso(id, curso);
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        service.eliminarCurso(id);
        return "redirect:/cursos";
    }
}
