package com.proyecto.mvcweb.controller.servicio_calculos;

import com.proyecto.mvcweb.model.servicio_calculos.CalculationResponse;
import com.proyecto.mvcweb.model.servicio_calculos.IntegrationRequest;
import com.proyecto.mvcweb.model.servicio_calculos.MatrixRequest;
import com.proyecto.mvcweb.service.servicio_calculos.CalculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculosController {

    @Autowired
    private CalculosService clientService;

    @PostMapping("/app/cientifico/matriz")
    @ResponseBody
    public CalculationResponse proxyMultiplicacion(@RequestBody MatrixRequest request) {
        return clientService.callMatrixMultiplication(request);
    }

    @PostMapping("/app/cientifico/integracion")
    @ResponseBody
    public CalculationResponse proxyIntegracion(@RequestBody IntegrationRequest request) {
        return clientService.callIntegration(request);
    }
}
