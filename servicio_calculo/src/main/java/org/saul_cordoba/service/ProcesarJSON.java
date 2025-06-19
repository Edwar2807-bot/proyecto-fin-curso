package org.saul_cordoba.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProcesarJSON implements IProcesarDatos {
    
    private String texto;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String procesar(String datos) {
        this.texto = datos;
        
        try {
            // Crear JSON con los datos procesados
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("tipo", "JSON");
            jsonNode.put("datos_originales", datos);
            jsonNode.put("datos_procesados", datos.toUpperCase());
            jsonNode.put("longitud", datos.length());
            jsonNode.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            String jsonResult = objectMapper.writeValueAsString(jsonNode);
            
            // Guardar en archivo plano
            guardarEnArchivo(jsonResult, "json");
            
            return jsonResult;
            
        } catch (Exception e) {
            return "Error procesando JSON: " + e.getMessage();
        }
    }
    
    private void guardarEnArchivo(String contenido, String tipo) {
        try {
            String nombreArchivo = "datos_procesados_" + tipo + "_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            
            try (FileWriter writer = new FileWriter("data/" + nombreArchivo)) {
                writer.write("=== PROCESAMIENTO " + tipo.toUpperCase() + " ===\n");
                writer.write("Timestamp: " + LocalDateTime.now() + "\n");
                writer.write("Contenido:\n");
                writer.write(contenido);
                writer.write("\n=== FIN ===\n");
            }
            
            System.out.println("Datos guardados en: data/" + nombreArchivo);
            
        } catch (IOException e) {
            System.err.println("Error guardando archivo: " + e.getMessage());
        }
    }
} 