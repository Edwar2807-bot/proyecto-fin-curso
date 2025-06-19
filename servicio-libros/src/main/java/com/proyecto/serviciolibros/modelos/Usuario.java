package com.proyecto.serviciolibros.modelos;

public class Usuario {
    private Long id;
    private String nombre;
    private Rol rol;

    public Usuario(Long id, String nombre, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Rol getRol() {
        return rol;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
