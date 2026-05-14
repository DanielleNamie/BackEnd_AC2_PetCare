package com.facens.ac2.entity.pet;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Veterinario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especialidade;

    @OneToMany(mappedBy = "veterinario")
    @JsonManagedReference("veterinario-consultas")
    @Builder.Default
    private List<Consulta> consultas = new ArrayList<>();
}
