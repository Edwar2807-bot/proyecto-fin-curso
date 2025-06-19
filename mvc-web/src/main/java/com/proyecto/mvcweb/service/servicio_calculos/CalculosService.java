package com.proyecto.mvcweb.service.servicio_calculos;


import com.proyecto.mvcweb.model.servicio_calculos.CalculationResponse;
import com.proyecto.mvcweb.model.servicio_calculos.IntegrationRequestDTO;
import com.proyecto.mvcweb.model.servicio_calculos.MatrixRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculosService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://servicio-calculo:8080/api/cientifico";

    public CalculationResponse calcularIntegracion(IntegrationRequestDTO dto) {
        return restTemplate.postForObject(
                BASE_URL + "/integracion",
                dto,
                CalculationResponse.class
        );
    }


    public CalculationResponse multiplicarMatrices(MatrixRequestDTO dto) {
        return restTemplate.postForObject(
                BASE_URL + "/matriz",
                dto,
                CalculationResponse.class
        );
    }

}
