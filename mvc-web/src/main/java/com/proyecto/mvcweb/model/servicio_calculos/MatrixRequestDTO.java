package com.proyecto.mvcweb.model.servicio_calculos;

public class MatrixRequestDTO {
    private double[][] matrixA;
    private double[][] matrixB;

    public double[][] getMatrixA() {
        return matrixA;
    }

    public void setMatrixA(double[][] matrixA) {
        this.matrixA = matrixA;
    }

    public double[][] getMatrixB() {
        return matrixB;
    }

    public void setMatrixB(double[][] matrixB) {
        this.matrixB = matrixB;
    }
}
