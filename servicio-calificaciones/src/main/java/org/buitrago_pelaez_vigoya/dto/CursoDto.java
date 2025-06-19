package org.buitrago_pelaez_vigoya.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.buitrago_pelaez_vigoya.enums.TipoNota;

@Data
public class CursoDto {
    private Long id;

    @NotBlank(message = "El nombre del curso no puede estar vacío")
    private String nombre;

    @NotNull(message = "El tipo de nota no puede ser nulo")
    private TipoNota tipoNota;
}