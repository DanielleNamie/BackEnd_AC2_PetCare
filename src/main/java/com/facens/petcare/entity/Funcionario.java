package com.facens.petcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    // unique = true: não permite dois funcionários com o mesmo e-mail
    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(length = 100)
    private String cargo;

    // N:1 — muitos funcionários pertencem a um setor
    // @JoinColumn cria a FK "setor_id" na tabela funcionario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id")
    private Setor setor;

    // N:M — muitos funcionários em muitos projetos
    // mappedBy aponta para o atributo "funcionarios" em Projeto
    @ManyToMany(mappedBy = "funcionarios")
    @Builder.Default
    private List<Projeto> projetos = new ArrayList<>();

    public Funcionario(String nome, String email, String cargo, Setor setor) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.setor = setor;
    }
}
