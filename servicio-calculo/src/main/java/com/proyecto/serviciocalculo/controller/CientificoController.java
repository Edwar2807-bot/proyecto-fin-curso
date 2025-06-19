package com.proyecto.serviciocalculo.controller;

import com.proyecto.serviciocalculo.dto.CalculationResponse;
import com.proyecto.serviciocalculo.dto.IntegrationRequest;
import com.proyecto.serviciocalculo.dto.MatrixRequest;
import com.proyecto.serviciocalculo.service.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cientifico")
public class CientificoController {
    @Autowired
    private CalculoService calculoService;

    @PostMapping("/matriz")
    public CalculationResponse multiplicarMatrices(@RequestBody MatrixRequest request) {
        return calculoService.multiplyMatrices(request.getMatrixA(), request.getMatrixB());
    }

    @PostMapping("/integracion")
    public CalculationResponse calcularIntegral(@RequestBody IntegrationRequest request) {
        return calculoService.integrate(
                request.getFunction(),
                request.getStart(),
                request.getEnd(),
                request.getIntervals()
        );
    }
}
