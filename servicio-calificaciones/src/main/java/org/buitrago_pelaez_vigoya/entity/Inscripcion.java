package org.buitrago_pelaez_vigoya.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.buitrago_pelaez_vigoya.enums.NotaCualitativaValor;

@Entity
@Table(name = "inscripciones", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"estudiante_id", "curso_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column
    private Double notaCuantitativa; // Rango 0-5

    @Enumerated(EnumType.STRING)
    @Column
    private NotaCualitativaValor notaCualitativa;

    // Podríamos añadir validaciones aquí o en el DTO/Servicio
    // para asegurar que solo una nota (cuantitativa o cualitativa) esté presente
    // y que la nota cuantitativa esté en el rango correcto.
}