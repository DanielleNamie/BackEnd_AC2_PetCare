package com.facens.ac2.entity.pet;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "animal")
    @JsonManagedReference
    @Builder.Default
    private List<Consulta> consultas = new ArrayList<>();
}
