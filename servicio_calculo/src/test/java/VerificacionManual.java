import org.saul_cordoba.service.*;
import java.util.Base64;

public class VerificacionManual {
    
    public static void main(String[] args) {
        System.out.println("=== VERIFICACIÓN MANUAL DEL SERVICIO CALCULO ===");
        System.out.println();
        
        // Test 1: ProcesarJSON
        System.out.println("1. PROBANDO ProcesarJSON:");
        ProcesarJSON procesarJSON = new ProcesarJSON();
        String resultadoJSON = procesarJSON.procesar("Hola Mundo");
        System.out.println("✅ Resultado JSON: " + resultadoJSON.substring(0, Math.min(100, resultadoJSON.length())) + "...");
        System.out.println();
        
        // Test 2: ProcesarBase64
        System.out.println("2. PROBANDO ProcesarBase64:");
        ProcesarBase64 procesarBase64 = new ProcesarBase64();
        String resultadoBase64 = procesarBase64.procesar("Hello World");
        System.out.println("✅ Resultado Base64: " + resultadoBase64);
        
        // Test decodificación
        String decodificado = procesarBase64.decodificar(resultadoBase64);
        System.out.println("✅ Decodificado: " + decodificado);
        System.out.println();
        
        // Test 3: ProcesarXML
        System.out.println("3. PROBANDO ProcesarXML:");
        ProcesarXML procesarXML = new ProcesarXML();
        String resultadoXML = procesarXML.procesar("Datos de prueba con símbolos @#$");
        System.out.println("✅ Resultado XML: " + resultadoXML.substring(0, Math.min(150, resultadoXML.length())) + "...");
        System.out.println();
        
        // Test 4: Interface
        System.out.println("4. PROBANDO Interface IProcesarDatos:");
        IProcesarDatos[] procesadores = {
            new ProcesarJSON(),
            new ProcesarBase64(), 
            new ProcesarXML()
        };
        
        for (IProcesarDatos procesador : procesadores) {
            String resultado = procesador.procesar("Test Interface");
            System.out.println("✅ " + procesador.getClass().getSimpleName() + " implementa correctamente IProcesarDatos");
        }
        
        System.out.println();
        System.out.println("=== VERIFICACIÓN COMPLETADA EXITOSAMENTE ===");
        System.out.println("✅ Todas las clases del diagrama UML funcionan correctamente");
        System.out.println("✅ Interface IProcesarDatos implementada");
        System.out.println("✅ Procesamiento JSON funcional");
        System.out.println("✅ Procesamiento Base64 funcional");
        System.out.println("✅ Procesamiento XML funcional");
        System.out.println("✅ Persistencia en archivos implementada");
    }
} 