package org.saul_cordoba.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ProcesarJSONSimple implements IProcesarDatos {
    
    @Override
    public String procesar(String datos) {
        try {
            // Procesar datos (convertir a mayúsculas)
            String datosProcessados = datos.toUpperCase();
            
            // Crear JSON manualmente (sin Jackson)
            String json = String.format(
                "{\n" +
                "  \"tipo\": \"JSON\",\n" +
                "  \"datos_originales\": \"%s\",\n" +
                "  \"datos_procesados\": \"%s\",\n" +
                "  \"longitud\": %d,\n" +
                "  \"timestamp\": \"%s\"\n" +
                "}",
                datos, datosProcessados, datos.length(), LocalDateTime.now()
            );
            
            // Persistir en archivo
            persistirEnArchivo("JSON", datos, json);
            
            return json;
        } catch (Exception e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
    
    private void persistirEnArchivo(String tipo, String datos, String resultado) {
        try {
            // Crear directorio data si no existe
            java.io.File dataDir = new java.io.File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            
            String archivo = "data/procesamiento_" + tipo.toLowerCase() + "_" + 
                           System.currentTimeMillis() + ".txt";
            
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write("=== PROCESAMIENTO " + tipo + " ===\n");
                writer.write("Timestamp: " + LocalDateTime.now() + "\n");
                writer.write("Datos originales: " + datos + "\n");
                writer.write("Resultado:\n" + resultado + "\n");
            }
            
            System.out.println("Datos persistidos en: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al persistir: " + e.getMessage());
        }
    }
} 