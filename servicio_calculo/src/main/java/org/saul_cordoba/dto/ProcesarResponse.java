package org.saul_cordoba.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ProcesarResponse {
    
    @JsonProperty("tipo")
    private String tipo;
    
    @JsonProperty("datos_originales")
    private String datosOriginales;
    
    @JsonProperty("resultado")
    private String resultado;
    
    @JsonProperty("mensaje")
    private String mensaje;
    
    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    public ProcesarResponse() {}
    
    public ProcesarResponse(String tipo, String datosOriginales, String resultado, String mensaje, LocalDateTime timestamp) {
        this.tipo = tipo;
        this.datosOriginales = datosOriginales;
        this.resultado = resultado;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }
    
    // Getters y Setters
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDatosOriginales() {
        return datosOriginales;
    }
    
    public void setDatosOriginales(String datosOriginales) {
        this.datosOriginales = datosOriginales;
    }
    
    public String getResultado() {
        return resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
} 