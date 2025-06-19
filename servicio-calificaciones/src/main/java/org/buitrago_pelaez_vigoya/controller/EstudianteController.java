package org.buitrago_pelaez_vigoya.controller;


import jakarta.validation.Valid;
import org.buitrago_pelaez_vigoya.dto.EstudianteDto;
import org.buitrago_pelaez_vigoya.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public ResponseEntity<EstudianteDto> crearEstudiante(@Valid @RequestBody EstudianteDto estudianteDto) {
        return new ResponseEntity<>(estudianteService.crearEstudiante(estudianteDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDto>> obtenerTodosLosEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodosLosEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDto> obtenerEstudiantePorId(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerEstudiantePorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<EstudianteDto> obtenerEstudiantePorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estudianteService.obtenerEstudiantePorCodigo(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDto> actualizarEstudiante(@PathVariable Long id, @Valid @RequestBody EstudianteDto estudianteDto) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, estudianteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}