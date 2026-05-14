package com.facens.ac2.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

import com.facens.ac2.entity.projeto.Projeto;
import com.facens.ac2.entity.projeto.Projeto.StatusProjeto;

@Builder
public record ProjetoResponse(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        StatusProjeto status,
        List<FuncionarioResumoResponse> funcionarios
) {

    public static ProjetoResponse from(Projeto projeto) {
        List<FuncionarioResumoResponse> funcionarios = projeto.getFuncionarios().stream()
                .map(f -> new FuncionarioResumoResponse(f.getId(), f.getNome(), f.getCargo()))
                .toList();

        return ProjetoResponse.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .status(projeto.getStatus())
                .funcionarios(funcionarios)
                .build();
    }
}
