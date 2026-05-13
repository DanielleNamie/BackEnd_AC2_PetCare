package com.facens.petcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// @Entity: diz ao JPA que esta classe vira uma tabela no banco
@Entity
@Table(name = "setor")
@Getter
@Setter
@NoArgsConstructor
public class Setor {

    // @Id + @GeneratedValue: chave primária com auto-incremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false): campo obrigatório no banco
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    // Um setor tem muitos funcionários (1:N)
    // mappedBy = "setor" aponta para o atributo na entidade Funcionario
    // cascade = PERSIST: ao salvar setor, salva funcionários junto (se houver)
    @OneToMany(mappedBy = "setor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Setor(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
