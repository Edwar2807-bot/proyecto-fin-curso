package com.proyecto.serviciolibros.modelos;

public class PrestamoDTO {
    public Long id;
    public Long usuarioId;
    public Long libroId;
    public int diasDesdePrestamo;
    public int diasParaDevolucion;
    public Integer diasDesdeDevolucion;

    public PrestamoDTO() {
    }

    public PrestamoDTO(Long id, Long usuarioId, Long libroId, int diasDesdePrestamo, int diasParaDevolucion, Integer diasDesdeDevolucion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
        this.diasDesdePrestamo = diasDesdePrestamo;
        this.diasParaDevolucion = diasParaDevolucion;
        this.diasDesdeDevolucion = diasDesdeDevolucion;
    }
}
