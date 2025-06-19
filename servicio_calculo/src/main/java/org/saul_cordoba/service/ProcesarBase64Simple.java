package org.saul_cordoba.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

public class ProcesarBase64Simple implements IProcesarDatos {
    
    @Override
    public String procesar(String datos) {
        try {
            // Codificar en Base64
            String datosBase64 = Base64.getEncoder().encodeToString(datos.getBytes());
            
            // Persistir en archivo
            persistirEnArchivo("BASE64", datos, datosBase64);
            
            return datosBase64;
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
    
    public String decodificar(String datosBase64) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(datosBase64);
            return new String(decodedBytes);
        } catch (Exception e) {
            return "ERROR al decodificar: " + e.getMessage();
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
                writer.write("Datos codificados: " + resultado + "\n");
                writer.write("Longitud original: " + datos.length() + "\n");
                writer.write("Longitud codificada: " + resultado.length() + "\n");
                
                // Mostrar decodificación como prueba
                String decodificado = decodificar(resultado);
                writer.write("Decodificación (prueba): " + decodificado + "\n");
            }
            
            System.out.println("Datos persistidos en: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al persistir: " + e.getMessage());
        }
    }
} 