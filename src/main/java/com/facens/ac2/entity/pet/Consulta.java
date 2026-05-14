package com.facens.ac2.entity.pet;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Consulta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Animal animal;
    @ManyToOne
    private Veterinario veterinario;
    private LocalDateTime dataHora;
    private String observacoes;
}
