package org.buitrago_pelaez_vigoya.service;


import org.buitrago_pelaez_vigoya.dto.CursoDto;
import org.buitrago_pelaez_vigoya.entity.Curso;
import org.buitrago_pelaez_vigoya.exception.ResourceNotFoundException;
import org.buitrago_pelaez_vigoya.repository.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    @Transactional
    public CursoDto crearCurso(CursoDto cursoDto) {
        Curso curso = new Curso();
        BeanUtils.copyProperties(cursoDto, curso);
        curso = cursoRepository.save(curso);
        BeanUtils.copyProperties(curso, cursoDto);
        return cursoDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDto> obtenerTodosLosCursos() {
        return cursoRepository.findAll().stream().map(curso -> {
            CursoDto dto = new CursoDto();
            BeanUtils.copyProperties(curso, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CursoDto obtenerCursoPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        CursoDto dto = new CursoDto();
        BeanUtils.copyProperties(curso, dto);
        return dto;
    }

    @Override
    @Transactional
    public CursoDto actualizarCurso(Long id, CursoDto cursoDto) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        BeanUtils.copyProperties(cursoDto, cursoExistente, "id");
        cursoExistente = cursoRepository.save(cursoExistente);
        BeanUtils.copyProperties(cursoExistente, cursoDto);
        return cursoDto;
    }

    @Override
    @Transactional
    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        cursoRepository.delete(curso);
    }
}