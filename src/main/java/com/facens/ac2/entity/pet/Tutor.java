package com.facens.ac2.entity.pet;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @OneToMany(mappedBy = "tutor")
    @JsonManagedReference
    @Builder.Default
    private List<Animal> animais = new ArrayList<>();
}
