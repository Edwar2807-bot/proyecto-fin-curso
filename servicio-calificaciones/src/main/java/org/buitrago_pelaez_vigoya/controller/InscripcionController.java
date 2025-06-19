package org.buitrago_pelaez_vigoya.controller;




import jakarta.validation.Valid;
import org.buitrago_pelaez_vigoya.dto.InscripcionDto;
import org.buitrago_pelaez_vigoya.dto.InscripcionRequestDto;
import org.buitrago_pelaez_vigoya.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @Autowired
    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<InscripcionDto> crearOActualizarInscripcion(@Valid @RequestBody InscripcionRequestDto inscripcionRequestDto) {
        return new ResponseEntity<>(inscripcionService.crearOActualizarInscripcion(inscripcionRequestDto), HttpStatus.OK); // OK o CREATED dependiendo de si siempre crea o puede actualizar
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<InscripcionDto>> obtenerInscripcionesPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(inscripcionService.obtenerInscripcionesPorCurso(cursoId));
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<InscripcionDto>> obtenerInscripcionesPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(inscripcionService.obtenerInscripcionesPorEstudiante(estudianteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDto> obtenerInscripcionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.obtenerInscripcionPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}