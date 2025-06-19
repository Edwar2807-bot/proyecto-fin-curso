package org.saul_cordoba.service;

/**
 * Interface para procesar diferentes tipos de datos
 * Según el diagrama UML del taller
 */
public interface IProcesarDatos {
    /**
     * Procesa los datos recibidos y retorna el resultado como String
     * @param datos Los datos a procesar
     * @return Resultado del procesamiento
     */
    String procesar(String datos);
} 