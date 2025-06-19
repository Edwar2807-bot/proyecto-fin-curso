package com.proyecto.mvcweb.model.servicio_calificaciones;


public class CursoDTO {
    private Long id;
    private String nombre;
    private String tipoNota; // "CUANTITATIVA" o "CUALITATIVA"

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }
}
