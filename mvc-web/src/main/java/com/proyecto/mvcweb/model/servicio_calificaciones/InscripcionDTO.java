package com.proyecto.mvcweb.model.servicio_calificaciones;

import com.proyecto.mvcweb.enums.NotaCualitativaValor;

public class InscripcionDTO {
    private Long id;
    private Long estudianteId;
    private Long cursoId;
    private Double notaCuantitativa;
    private NotaCualitativaValor notaCualitativa;
    private String nombreEstudiante;
    private String nombreCurso;

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Double getNotaCuantitativa() {
        return notaCuantitativa;
    }

    public void setNotaCuantitativa(Double notaCuantitativa) {
        this.notaCuantitativa = notaCuantitativa;
    }

    public NotaCualitativaValor getNotaCualitativa() {
        return notaCualitativa;
    }

    public void setNotaCualitativa(NotaCualitativaValor notaCualitativa) {
        this.notaCualitativa = notaCualitativa;
    }
}
