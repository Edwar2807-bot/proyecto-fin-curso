package com.proyecto.mvcweb.service.servicio_calculos;

import com.proyecto.mvcweb.model.servicio_calculos.CalculationResponse;
import com.proyecto.mvcweb.model.servicio_calculos.IntegrationRequest;
import com.proyecto.mvcweb.model.servicio_calculos.MatrixRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculosService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.cientifico.url}")
    private String cientificoServiceUrl;

    public CalculationResponse callMatrixMultiplication(MatrixRequest request) {
        return restTemplate.postForObject(cientificoServiceUrl + "/matriz", request, CalculationResponse.class);
    }
    public CalculationResponse callIntegration(IntegrationRequest request) {
        return restTemplate.postForObject(cientificoServiceUrl + "/integracion", request, CalculationResponse.class);
    }
}
