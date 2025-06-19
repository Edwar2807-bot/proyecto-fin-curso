package org.buitrago_pelaez_vigoya.service;

import org.buitrago_pelaez_vigoya.dto.EstudianteDto;
import org.buitrago_pelaez_vigoya.entity.Estudiante;
import org.buitrago_pelaez_vigoya.exception.BadRequestException;
import org.buitrago_pelaez_vigoya.exception.ResourceNotFoundException;
import org.buitrago_pelaez_vigoya.repository.EstudianteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    @Transactional
    public EstudianteDto crearEstudiante(EstudianteDto estudianteDto) {
        if (estudianteRepository.findByCodigoEstudiante(estudianteDto.getCodigoEstudiante()).isPresent()) {
            throw new BadRequestException("Ya existe un estudiante con el código: " + estudianteDto.getCodigoEstudiante());
        }
        Estudiante estudiante = new Estudiante();
        BeanUtils.copyProperties(estudianteDto, estudiante);
        estudiante = estudianteRepository.save(estudiante);
        BeanUtils.copyProperties(estudiante, estudianteDto);
        return estudianteDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDto> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll().stream().map(estudiante -> {
            EstudianteDto dto = new EstudianteDto();
            BeanUtils.copyProperties(estudiante, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDto obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + id));
        EstudianteDto dto = new EstudianteDto();
        BeanUtils.copyProperties(estudiante, dto);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDto obtenerEstudiantePorCodigo(String codigo) {
        Estudiante estudiante = estudianteRepository.findByCodigoEstudiante(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con código: " + codigo));
        EstudianteDto dto = new EstudianteDto();
        BeanUtils.copyProperties(estudiante, dto);
        return dto;
    }

    @Override
    @Transactional
    public EstudianteDto actualizarEstudiante(Long id, EstudianteDto estudianteDto) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + id));

        // Verificar si el nuevo código de estudiante ya existe en otro estudiante
        estudianteRepository.findByCodigoEstudiante(estudianteDto.getCodigoEstudiante())
                .ifPresent(e -> {
                    if (!e.getId().equals(id)) {
                        throw new BadRequestException("El código de estudiante " + estudianteDto.getCodigoEstudiante() + " ya está en uso por otro estudiante.");
                    }
                });

        BeanUtils.copyProperties(estudianteDto, estudianteExistente, "id"); // No copiar el ID
        estudianteExistente = estudianteRepository.save(estudianteExistente);
        BeanUtils.copyProperties(estudianteExistente, estudianteDto);
        return estudianteDto;
    }

    @Override
    @Transactional
    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + id));
        // Considerar si hay que eliminar inscripciones asociadas o lanzar error si existen
        // Por ahora, cascade debería manejarlas si está configurado en la entidad
        estudianteRepository.delete(estudiante);
    }
}