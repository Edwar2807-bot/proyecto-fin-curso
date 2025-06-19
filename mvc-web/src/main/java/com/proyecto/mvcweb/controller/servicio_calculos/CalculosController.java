package com.proyecto.mvcweb.controller.servicio_calculos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.mvcweb.model.servicio_calculos.CalculationResponse;
import com.proyecto.mvcweb.model.servicio_calculos.IntegrationRequestDTO;
import com.proyecto.mvcweb.model.servicio_calculos.MatrixRequestDTO;
import com.proyecto.mvcweb.service.servicio_calculos.CalculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calculo")
public class CalculosController {

    @Autowired
    private CalculosService service;

    public CalculosController(CalculosService service) {
        this.service = service;
    }

    @GetMapping("/integracion")
    public String mostrarIntegracion(Model model) {
        return "calculos/integracion";
    }


    @PostMapping("/integracion")
    public String calcularIntegracion(@ModelAttribute IntegrationRequestDTO dto, Model model) {
        CalculationResponse resultado = service.calcularIntegracion(dto);
        model.addAttribute("resultadoIntegracion", resultado);
        return "calculos/integracion";
    }

    @GetMapping("/matriz")
    public String mostrarMatriz(Model model) {
        return "calculos/matriz";
    }

    @PostMapping("/matriz")
    public String calcularMultiplicacion(
            @RequestParam("matrixA") String matrixAJson,
            @RequestParam("matrixB") String matrixBJson,
            Model model) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            double[][] matrixA = mapper.readValue(matrixAJson, double[][].class);
            double[][] matrixB = mapper.readValue(matrixBJson, double[][].class);

            MatrixRequestDTO dto = new MatrixRequestDTO();
            dto.setMatrixA(matrixA);
            dto.setMatrixB(matrixB);

            CalculationResponse resultado = service.multiplicarMatrices(dto);
            model.addAttribute("resultadoMatriz", resultado);
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar las matrices: " + e.getMessage());
        }

        return "calculos/matriz";
    }
}
