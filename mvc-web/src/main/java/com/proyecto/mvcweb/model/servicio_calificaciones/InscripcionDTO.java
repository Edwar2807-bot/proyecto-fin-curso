package com.proyecto.mvcweb.model.servicio_calificaciones;

public class InscripcionDTO {
    private Long id;
    private Long estudianteId;
    private Long cursoId;
    private Double notaCuantitativa;
    private String notaCualitativa;
    private String nombreEstudiante;
    private String nombreCurso;
    private String tipoNotaCurso;
    private EstudianteDTO estudiantes;
    private CursoDTO cursos;

    public EstudianteDTO getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(EstudianteDTO estudiantes) {
        this.estudiantes = estudiantes;
    }

    public CursoDTO getCursos() {
        return cursos;
    }

    public void setCursos(CursoDTO cursos) {
        this.cursos = cursos;
    }

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

    public String getTipoNotaCurso() {
        return tipoNotaCurso;
    }

    public void setTipoNotaCurso(String tipoNotaCurso) {
        this.tipoNotaCurso = tipoNotaCurso;
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

    public String getNotaCualitativa() {
        return notaCualitativa;
    }

    public void setNotaCualitativa(String notaCualitativa) {
        this.notaCualitativa = notaCualitativa;
    }
}
