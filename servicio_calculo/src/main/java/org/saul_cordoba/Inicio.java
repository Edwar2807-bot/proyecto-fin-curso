package org.saul_cordoba;

import org.saul_cordoba.service.IProcesarDatos;
import org.saul_cordoba.service.ProcesarJSON;
import org.saul_cordoba.service.ProcesarBase64;
import org.saul_cordoba.service.ProcesarXML;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Inicio implements CommandLineRunner {
    
    public static void main(String[] args) {
        System.out.println("=== Servicio de Cálculo Científico ===");
        System.out.println("Iniciando procesamiento de datos...");
        
        Inicio inicio = new Inicio();
        inicio.mostrarInformacion(new ProcesarJSON());
        inicio.mostrarInformacion(new ProcesarBase64());
        inicio.mostrarInformacion(new ProcesarXML());
    }
    
    public void mostrarInformacion(IProcesarDatos procesador) {
        System.out.println("Procesando datos...");
        String resultado = procesador.procesar("Datos de ejemplo para procesar");
        System.out.println("Resultado: " + resultado);
        System.out.println("---");
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Aplicación Spring Boot iniciada - Servicio de Cálculo Científico");
        mostrarInformacion(new ProcesarJSON());
        mostrarInformacion(new ProcesarBase64());
        mostrarInformacion(new ProcesarXML());
    }
} 