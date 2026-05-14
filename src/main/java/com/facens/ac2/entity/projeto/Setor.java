package com.facens.ac2.entity.projeto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "setor")
@Getter
@Setter
@NoArgsConstructor
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Setor(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
