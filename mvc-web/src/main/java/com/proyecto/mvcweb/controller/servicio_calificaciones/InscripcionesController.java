package com.proyecto.mvcweb.controller.servicio_calificaciones;



import com.proyecto.mvcweb.model.servicio_calificaciones.InscripcionDTO;
import com.proyecto.mvcweb.service.servicio_calificaciones.CursoService;
import com.proyecto.mvcweb.service.servicio_calificaciones.EstudianteService;
import com.proyecto.mvcweb.service.servicio_calificaciones.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inscripciones")
public class InscripcionesController {

    private final InscripcionService service;
    private final EstudianteService estudianteService;
    private final CursoService cursoService;

    public InscripcionesController(InscripcionService service, EstudianteService estudianteService, CursoService cursoService) {
        this.service = service;
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public String inicio(Model model) {
        return "inscripciones/index";
    }

    @GetMapping("/crear")
    public String mostrarFormularioInscripcion(Model model) {
        model.addAttribute("inscripcionDTO", new InscripcionDTO());
        model.addAttribute("estudiantes", estudianteService.obtenerEstudiantes());
        model.addAttribute("cursos", cursoService.obtenerCursos());
        return "inscripciones/nueva";
    }


    @GetMapping("/curso")
    public String listarPorCurso(@RequestParam  Long cursoId, Model model) {
        List<InscripcionDTO> inscripciones = service.obtenerInscripcionesPorCurso(cursoId);
        model.addAttribute("inscripciones", inscripciones);
        return "inscripciones/lista";
    }

    @GetMapping("/estudiante")
    public String listarPorEstudiante(@RequestParam Long estudianteId, Model model) {
        List<InscripcionDTO> inscripciones = service.obtenerInscripcionesPorEstudiante(estudianteId);
        model.addAttribute("inscripciones", inscripciones);
        return "inscripciones/lista";
    }


    @PostMapping("/crear")
    public String crear(@ModelAttribute InscripcionDTO inscripcionDTO) {
        service.crearInscripcion(inscripcionDTO);
        return "redirect:/inscripciones/curso?cursoId=" + inscripcionDTO.getCursoId();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminarInscripcion(id);
        return "redirect:/inscripciones";
    }

}