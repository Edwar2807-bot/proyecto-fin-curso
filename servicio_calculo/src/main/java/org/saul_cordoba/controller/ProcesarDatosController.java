package org.saul_cordoba.controller;

import org.saul_cordoba.dto.ProcesarRequest;
import org.saul_cordoba.dto.ProcesarResponse;
import org.saul_cordoba.service.ProcesarJSON;
import org.saul_cordoba.service.ProcesarBase64;
import org.saul_cordoba.service.ProcesarXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/calculo")
@CrossOrigin(origins = "*")
public class ProcesarDatosController {

    @Autowired
    private ProcesarJSON procesarJSON;
    
    @Autowired
    private ProcesarBase64 procesarBase64;
    
    @Autowired
    private ProcesarXML procesarXML;

    @GetMapping("/status")
    public String getStatus() {
        return "Servicio de Cálculo Científico - Funcionando correctamente";
    }

    @PostMapping("/procesar/json")
    public ResponseEntity<ProcesarResponse> procesarJSON(@RequestBody ProcesarRequest request) {
        try {
            String resultado = procesarJSON.procesar(request.getDatos());
            
            ProcesarResponse response = new ProcesarResponse(
                "JSON",
                request.getDatos(),
                resultado,
                "Procesamiento exitoso",
                LocalDateTime.now()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ProcesarResponse response = new ProcesarResponse(
                "JSON",
                request.getDatos(),
                null,
                "Error: " + e.getMessage(),
                LocalDateTime.now()
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/procesar/base64")
    public ResponseEntity<ProcesarResponse> procesarBase64(@RequestBody ProcesarRequest request) {
        try {
            String resultado = procesarBase64.procesar(request.getDatos());
            
            ProcesarResponse response = new ProcesarResponse(
                "BASE64",
                request.getDatos(),
                resultado,
                "Procesamiento exitoso",
                LocalDateTime.now()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ProcesarResponse response = new ProcesarResponse(
                "BASE64",
                request.getDatos(),
                null,
                "Error: " + e.getMessage(),
                LocalDateTime.now()
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/procesar/xml")
    public ResponseEntity<ProcesarResponse> procesarXML(@RequestBody ProcesarRequest request) {
        try {
            String resultado = procesarXML.procesar(request.getDatos());
            
            ProcesarResponse response = new ProcesarResponse(
                "XML",
                request.getDatos(),
                resultado,
                "Procesamiento exitoso",
                LocalDateTime.now()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ProcesarResponse response = new ProcesarResponse(
                "XML",
                request.getDatos(),
                null,
                "Error: " + e.getMessage(),
                LocalDateTime.now()
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/procesar/base64/decodificar")
    public ResponseEntity<ProcesarResponse> decodificarBase64(@RequestBody ProcesarRequest request) {
        try {
            String resultado = procesarBase64.decodificar(request.getDatos());
            
            ProcesarResponse response = new ProcesarResponse(
                "BASE64_DECODE",
                request.getDatos(),
                resultado,
                "Decodificación exitosa",
                LocalDateTime.now()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ProcesarResponse response = new ProcesarResponse(
                "BASE64_DECODE",
                request.getDatos(),
                null,
                "Error: " + e.getMessage(),
                LocalDateTime.now()
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
} 