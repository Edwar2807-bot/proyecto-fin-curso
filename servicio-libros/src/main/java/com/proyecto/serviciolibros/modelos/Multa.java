package com.proyecto.serviciolibros.modelos;

public class Multa {
    private String nombreUsuario;
    private String tituloLibro;
    private long diasRetraso;
    private double montoMulta;

    public Multa() {
    }

    public Multa(String nombreUsuario, String tituloLibro, long diasRetraso, double montoMulta) {
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
        this.diasRetraso = diasRetraso;
        this.montoMulta = montoMulta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public long getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(long diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public double getMontoMulta() {
        return montoMulta;
    }

    public void setMontoMulta(double montoMulta) {
        this.montoMulta = montoMulta;
    }
}
