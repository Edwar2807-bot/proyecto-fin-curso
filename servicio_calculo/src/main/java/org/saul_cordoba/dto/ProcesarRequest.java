package org.saul_cordoba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcesarRequest {
    
    @JsonProperty("datos")
    private String datos;
    
    public ProcesarRequest() {}
    
    public ProcesarRequest(String datos) {
        this.datos = datos;
    }
    
    public String getDatos() {
        return datos;
    }
    
    public void setDatos(String datos) {
        this.datos = datos;
    }
} 