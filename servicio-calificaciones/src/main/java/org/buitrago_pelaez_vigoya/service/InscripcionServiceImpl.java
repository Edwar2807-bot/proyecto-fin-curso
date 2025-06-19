package org.buitrago_pelaez_vigoya.service;



import org.buitrago_pelaez_vigoya.dto.InscripcionDto;
import org.buitrago_pelaez_vigoya.dto.InscripcionDto;
import org.buitrago_pelaez_vigoya.dto.InscripcionRequestDto;
import org.buitrago_pelaez_vigoya.entity.Curso;
import org.buitrago_pelaez_vigoya.entity.Estudiante;
import org.buitrago_pelaez_vigoya.entity.Inscripcion;
import org.buitrago_pelaez_vigoya.enums.TipoNota;
import org.buitrago_pelaez_vigoya.exception.BadRequestException;
import org.buitrago_pelaez_vigoya.exception.ResourceNotFoundException;
import org.buitrago_pelaez_vigoya.repository.CursoRepository;
import org.buitrago_pelaez_vigoya.repository.EstudianteRepository;
import org.buitrago_pelaez_vigoya.repository.InscripcionRepository;
import org.buitrago_pelaez_vigoya.service.InscripcionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository,
                                  EstudianteRepository estudianteRepository,
                                  CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    @Transactional
    public InscripcionDto crearOActualizarInscripcion(InscripcionRequestDto requestDto) {
        Estudiante estudiante = estudianteRepository.findById(requestDto.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + requestDto.getEstudianteId()));
        Curso curso = cursoRepository.findById(requestDto.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + requestDto.getCursoId()));

        // Validar tipo de nota
        if (curso.getTipoNota() == TipoNota.CUANTITATIVA) {
            if (requestDto.getNotaCualitativa() != null) {
                throw new BadRequestException("El curso '" + curso.getNombre() + "' es de nota cuantitativa. No se puede ingresar nota cualitativa.");
            }
            if (requestDto.getNotaCuantitativa() != null && (requestDto.getNotaCuantitativa() < 0 || requestDto.getNotaCuantitativa() > 5)) {
                throw new BadRequestException("La nota cuantitativa debe estar entre 0 y 5.");
            }
        } else { // TipoNota.CUALITATIVA
            if (requestDto.getNotaCuantitativa() != null) {
                throw new BadRequestException("El curso '" + curso.getNombre() + "' es de nota cualitativa. No se puede ingresar nota cuantitativa.");
            }
            if (requestDto.getNotaCualitativa() == null && (requestDto.getNotaCuantitativa() == null) ) {
                // Permitir inscribir sin nota inicialmente, o requerir una nota pendiente?
                // Por ahora permitimos inscribir sin nota, se puede actualizar luego.
            }
        }

        Optional<Inscripcion> inscripcionExistenteOpt = inscripcionRepository.findByEstudianteIdAndCursoId(estudiante.getId(), curso.getId());
        Inscripcion inscripcion;

        if (inscripcionExistenteOpt.isPresent()) {
            inscripcion = inscripcionExistenteOpt.get();
        } else {
            inscripcion = new Inscripcion();
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCurso(curso);
        }

        inscripcion.setNotaCuantitativa(requestDto.getNotaCuantitativa());
        inscripcion.setNotaCualitativa(requestDto.getNotaCualitativa());

        inscripcion = inscripcionRepository.save(inscripcion);
        return mapToDto(inscripcion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionDto> obtenerInscripcionesPorCurso(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId);
        }
        return inscripcionRepository.findByCursoId(cursoId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionDto> obtenerInscripcionesPorEstudiante(Long estudianteId) {
        if (!estudianteRepository.existsById(estudianteId)) {
            throw new ResourceNotFoundException("Estudiante no encontrado con ID: " + estudianteId);
        }
        return inscripcionRepository.findByEstudianteId(estudianteId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InscripcionDto obtenerInscripcionPorId(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
        return mapToDto(inscripcion);
    }

    @Override
    @Transactional
    public void eliminarInscripcion(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
        inscripcionRepository.delete(inscripcion);
    }

    private InscripcionDto mapToDto(Inscripcion inscripcion) {
        InscripcionDto dto = new InscripcionDto();
        BeanUtils.copyProperties(inscripcion, dto);
        dto.setEstudianteId(inscripcion.getEstudiante().getId());
        dto.setNombreEstudiante(inscripcion.getEstudiante().getNombreCompleto());
        dto.setCursoId(inscripcion.getCurso().getId());
        dto.setNombreCurso(inscripcion.getCurso().getNombre());
        dto.setTipoNotaCurso(inscripcion.getCurso().getTipoNota().name());
        return dto;
    }
}