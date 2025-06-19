package org.buitrago_pelaez_vigoya.controller;


import jakarta.validation.Valid;
import org.buitrago_pelaez_vigoya.dto.CursoDto;
import org.buitrago_pelaez_vigoya.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<CursoDto> crearCurso(@Valid @RequestBody CursoDto cursoDto) {
        return new ResponseEntity<>(cursoService.crearCurso(cursoDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> obtenerTodosLosCursos() {
        return ResponseEntity.ok(cursoService.obtenerTodosLosCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> obtenerCursoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerCursoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDto> actualizarCurso(@PathVariable Long id, @Valid @RequestBody CursoDto cursoDto) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, cursoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}