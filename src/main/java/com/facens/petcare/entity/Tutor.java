package com.facens.petcare.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tutor {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String nome;
 private String telefone;
 @OneToMany(mappedBy = "tutor")
 private List<Animal> animais = new ArrayList<>();
}
