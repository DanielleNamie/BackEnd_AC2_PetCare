package com.facens.ac2.entity.pet;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especie;
    @ManyToOne
    @JsonBackReference
    private Tutor tutor;
}
