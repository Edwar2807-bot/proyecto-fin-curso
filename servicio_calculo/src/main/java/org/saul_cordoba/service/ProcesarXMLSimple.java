package org.saul_cordoba.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ProcesarXMLSimple implements IProcesarDatos {
    
    @Override
    public String procesar(String datos) {
        try {
            // Analizar caracteres especiales
            int especiales = contarCaracteresEspeciales(datos);
            int alfanumericos = datos.replaceAll("[^a-zA-Z0-9]", "").length();
            
            // Crear XML manualmente
            String xml = String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<procesamiento>\n" +
                "  <tipo>XML</tipo>\n" +
                "  <timestamp>%s</timestamp>\n" +
                "  <datos_originales><![CDATA[%s]]></datos_originales>\n" +
                "  <analisis>\n" +
                "    <longitud_total>%d</longitud_total>\n" +
                "    <caracteres_alfanumericos>%d</caracteres_alfanumericos>\n" +
                "    <caracteres_especiales>%d</caracteres_especiales>\n" +
                "  </analisis>\n" +
                "  <datos_procesados><![CDATA[%s]]></datos_procesados>\n" +
                "</procesamiento>",
                LocalDateTime.now(),
                datos,
                datos.length(),
                alfanumericos,
                especiales,
                datos.toUpperCase()
            );
            
            // Persistir en archivo XML
            persistirEnArchivo("XML", datos, xml);
            
            return xml;
        } catch (Exception e) {
            return "<error>" + e.getMessage() + "</error>";
        }
    }
    
    private int contarCaracteresEspeciales(String texto) {
        return texto.replaceAll("[a-zA-Z0-9\\s]", "").length();
    }
    
    private void persistirEnArchivo(String tipo, String datos, String resultado) {
        try {
            // Crear directorio data si no existe
            java.io.File dataDir = new java.io.File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            
            String archivo = "data/procesamiento_" + tipo.toLowerCase() + "_" + 
                           System.currentTimeMillis() + ".xml";
            
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(resultado);
            }
            
            System.out.println("Datos persistidos en: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al persistir: " + e.getMessage());
        }
    }
} 