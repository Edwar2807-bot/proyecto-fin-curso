package org.buitrago_pelaez_vigoya.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstudianteDto {
    private Long id;

    @NotBlank(message = "El nombre completo no puede estar vacío")
    private String nombreCompleto;

    @NotBlank(message = "El código de estudiante no puede estar vacío")
    private String codigoEstudiante;
}