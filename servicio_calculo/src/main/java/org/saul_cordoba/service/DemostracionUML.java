package org.saul_cordoba.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

public class DemostracionUML {
    
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   DEMOSTRACION DEL DIAGRAMA UML - TALLER UNIVERSITARIO");
        System.out.println("============================================================");
        System.out.println();
        
        // Crear instancias de las implementaciones
        IProcesarDatos procesarJSON = new ProcesarJSONSimple();
        IProcesarDatos procesarBase64 = new ProcesarBase64Simple();
        IProcesarDatos procesarXML = new ProcesarXMLSimple();
        
        String datosEjemplo = "Hola Mundo - Taller UML";
        
        System.out.println(">> Datos a procesar: \"" + datosEjemplo + "\"");
        System.out.println();
        
        // Demostrar ProcesarJSON
        System.out.println("1. PROCESAMIENTO JSON:");
        System.out.println("----------------------------------------");
        String resultadoJSON = procesarJSON.procesar(datosEjemplo);
        System.out.println(resultadoJSON);
        System.out.println();
        
        // Demostrar ProcesarBase64
        System.out.println("2. PROCESAMIENTO BASE64:");
        System.out.println("----------------------------------------");
        String resultadoBase64 = procesarBase64.procesar(datosEjemplo);
        System.out.println("Resultado: " + resultadoBase64);
        System.out.println();
        
        // Demostrar ProcesarXML
        System.out.println("3. PROCESAMIENTO XML:");
        System.out.println("----------------------------------------");
        String resultadoXML = procesarXML.procesar(datosEjemplo);
        System.out.println(resultadoXML);
        System.out.println();
        
        System.out.println(">> DEMOSTRACION COMPLETADA");
        System.out.println(">> Revisa el directorio 'data/' para ver los archivos persistidos");
        System.out.println();
        System.out.println(">> DIAGRAMA UML IMPLEMENTADO EXITOSAMENTE:");
        System.out.println("   |- Interface: IProcesarDatos");
        System.out.println("   |- Implementacion 1: ProcesarJSON");
        System.out.println("   |- Implementacion 2: ProcesarBase64");
        System.out.println("   |- Implementacion 3: ProcesarXML");
        System.out.println();
    }
    
    // Implementación local de ProcesarJSON (sin dependencias externas)
    static class ProcesarJSONLocal implements IProcesarDatos {
        @Override
        public String procesar(String datos) {
            String datosProcessados = datos.toUpperCase();
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
            
            persistirEnArchivo("JSON", datos, json);
            return json;
        }
    }
    
    // Implementación local de ProcesarBase64
    static class ProcesarBase64Local implements IProcesarDatos {
        @Override
        public String procesar(String datos) {
            String encoded = Base64.getEncoder().encodeToString(datos.getBytes());
            String resultado = "Base64 Encoded: " + encoded;
            
            persistirEnArchivo("BASE64", datos, resultado);
            return resultado;
        }
    }
    
    // Implementación local de ProcesarXML
    static class ProcesarXMLLocal implements IProcesarDatos {
        @Override
        public String procesar(String datos) {
            String xml = String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<procesamiento>\n" +
                "  <tipo>XML</tipo>\n" +
                "  <datos_originales><![CDATA[%s]]></datos_originales>\n" +
                "  <datos_procesados><![CDATA[%s]]></datos_procesados>\n" +
                "  <longitud>%d</longitud>\n" +
                "  <timestamp>%s</timestamp>\n" +
                "</procesamiento>",
                datos, datos.toUpperCase(), datos.length(), LocalDateTime.now()
            );
            
            persistirEnArchivo("XML", datos, xml);
            return xml;
        }
    }
    
    private static void persistirEnArchivo(String tipo, String datos, String resultado) {
        try {
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
            
            System.out.println("✅ Persistido en: " + archivo);
        } catch (IOException e) {
            System.err.println("❌ Error al persistir: " + e.getMessage());
        }
    }
} 