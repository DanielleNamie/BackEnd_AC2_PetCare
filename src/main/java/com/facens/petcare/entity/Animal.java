package com.facens.petcare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Animal {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String nome;
 private String especie;
 @ManyToOne
 private Tutor tutor;
}
