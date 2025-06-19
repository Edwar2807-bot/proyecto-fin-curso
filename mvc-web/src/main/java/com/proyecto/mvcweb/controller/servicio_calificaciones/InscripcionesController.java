package com.proyecto.mvcweb.controller.servicio_calificaciones;



import com.proyecto.mvcweb.model.servicio_calificaciones.InscripcionDTO;
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

    public InscripcionesController(InscripcionService service) {
        this.service = service;
    }

    @GetMapping("/curso/{cursoId}")
    public String listarPorCurso(@PathVariable Long cursoId, Model model) {
        List<InscripcionDTO> inscripciones = service.obtenerInscripcionesPorCurso(cursoId);
        model.addAttribute("inscripciones", inscripciones);
        return "inscripciones/porCurso";
    }

    @GetMapping("/estudiante/{estudianteId}")
    public String listarPorEstudiante(@PathVariable Long estudianteId, Model model) {
        List<InscripcionDTO> inscripciones = service.obtenerInscripcionesPorEstudiante(estudianteId);
        model.addAttribute("inscripciones", inscripciones);
        return "inscripciones/porEstudiante";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute InscripcionDTO inscripcionDTO) {
        service.crearInscripcion(inscripcionDTO);
        return "redirect:/inscripciones/curso/" + inscripcionDTO.getCursoId();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminarInscripcion(id);
        return "redirect:/inscripciones";
    }
}