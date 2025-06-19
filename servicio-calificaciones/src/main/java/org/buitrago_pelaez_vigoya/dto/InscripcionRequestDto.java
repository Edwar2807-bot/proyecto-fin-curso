package org.buitrago_pelaez_vigoya.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.buitrago_pelaez_vigoya.enums.NotaCualitativaValor;

@Data
public class InscripcionRequestDto {
    @NotNull(message = "El ID del estudiante no puede ser nulo")
    private Long estudianteId;

    @NotNull(message = "El ID del curso no puede ser nulo")
    private Long cursoId;

    // Solo uno de estos debería ser proporcionado, se validará en el servicio
    @Min(value = 0, message = "La nota cuantitativa debe ser como mínimo 0")
    @Max(value = 5, message = "La nota cuantitativa debe ser como máximo 5")
    private Double notaCuantitativa;

    private NotaCualitativaValor notaCualitativa;
}