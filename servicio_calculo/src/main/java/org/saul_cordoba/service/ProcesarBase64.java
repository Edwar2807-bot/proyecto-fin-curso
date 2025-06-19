package org.saul_cordoba.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class ProcesarBase64 implements IProcesarDatos {
    
    private String texto;
    
    @Override
    public String procesar(String datos) {
        this.texto = datos;
        
        try {
            // Codificar a Base64
            String datosBase64 = Base64.getEncoder().encodeToString(datos.getBytes());
            
            // Crear resultado con información adicional
            StringBuilder resultado = new StringBuilder();
            resultado.append("=== PROCESAMIENTO BASE64 ===\n");
            resultado.append("Datos originales: ").append(datos).append("\n");
            resultado.append("Datos codificados: ").append(datosBase64).append("\n");
            resultado.append("Longitud original: ").append(datos.length()).append("\n");
            resultado.append("Longitud codificada: ").append(datosBase64.length()).append("\n");
            resultado.append("Timestamp: ").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            String resultadoFinal = resultado.toString();
            
            // Guardar en archivo plano
            guardarEnArchivo(resultadoFinal, "base64");
            
            return datosBase64;
            
        } catch (Exception e) {
            return "Error procesando Base64: " + e.getMessage();
        }
    }
    
    private void guardarEnArchivo(String contenido, String tipo) {
        try {
            String nombreArchivo = "datos_procesados_" + tipo + "_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            
            try (FileWriter writer = new FileWriter("data/" + nombreArchivo)) {
                writer.write(contenido);
                writer.write("\n=== FIN ===\n");
            }
            
            System.out.println("Datos guardados en: data/" + nombreArchivo);
            
        } catch (IOException e) {
            System.err.println("Error guardando archivo: " + e.getMessage());
        }
    }
    
    public String decodificar(String datosBase64) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(datosBase64);
            return new String(decodedBytes);
        } catch (Exception e) {
            return "Error decodificando Base64: " + e.getMessage();
        }
    }
} 