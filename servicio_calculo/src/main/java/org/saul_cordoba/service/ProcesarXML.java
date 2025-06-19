package org.saul_cordoba.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProcesarXML implements IProcesarDatos {
    
    private String texto;
    
    @Override
    public String procesar(String datos) {
        this.texto = datos;
        
        try {
            // Crear XML con los datos procesados
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xmlBuilder.append("<procesamiento>\n");
            xmlBuilder.append("  <tipo>XML</tipo>\n");
            xmlBuilder.append("  <timestamp>").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("</timestamp>\n");
            xmlBuilder.append("  <datos_originales><![CDATA[").append(datos).append("]]></datos_originales>\n");
            xmlBuilder.append("  <datos_procesados><![CDATA[").append(datos.toLowerCase().replace(" ", "_")).append("]]></datos_procesados>\n");
            xmlBuilder.append("  <longitud>").append(datos.length()).append("</longitud>\n");
            xmlBuilder.append("  <caracteres_especiales>").append(contarCaracteresEspeciales(datos)).append("</caracteres_especiales>\n");
            xmlBuilder.append("</procesamiento>");
            
            String xmlResult = xmlBuilder.toString();
            
            // Guardar en archivo plano
            guardarEnArchivo(xmlResult, "xml");
            
            return xmlResult;
            
        } catch (Exception e) {
            return "Error procesando XML: " + e.getMessage();
        }
    }
    
    private int contarCaracteresEspeciales(String texto) {
        int count = 0;
        for (char c : texto.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                count++;
            }
        }
        return count;
    }
    
    private void guardarEnArchivo(String contenido, String tipo) {
        try {
            String nombreArchivo = "datos_procesados_" + tipo + "_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xml";
            
            try (FileWriter writer = new FileWriter("data/" + nombreArchivo)) {
                writer.write(contenido);
            }
            
            System.out.println("Datos guardados en: data/" + nombreArchivo);
            
        } catch (IOException e) {
            System.err.println("Error guardando archivo: " + e.getMessage());
        }
    }
} 