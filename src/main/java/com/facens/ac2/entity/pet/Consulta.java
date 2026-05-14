package com.facens.ac2.entity.pet;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Consulta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference("animal-consultas")
    private Animal animal;

    @ManyToOne
    @JsonBackReference("veterinario-consultas")
    private Veterinario veterinario;
    
    private LocalDateTime dataHora;
    private String observacoes;
    
}
