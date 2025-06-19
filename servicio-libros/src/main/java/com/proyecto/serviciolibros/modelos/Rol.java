package com.proyecto.serviciolibros.modelos;

public enum Rol {
    DOCENTE(5000),
    ESTUDIANTE(1000),
    EMPLEADO(2500);

    private final double multaPorDia;

    Rol(double multaPorDia) {
        this.multaPorDia = multaPorDia;
    }

    public double getMultaPorDia() {
        return multaPorDia;
    }
}
