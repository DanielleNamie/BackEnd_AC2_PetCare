package com.facens.petcare.dto;

import com.facens.petcare.entity.Projeto.StatusProjeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
import java.time.LocalDate;
import java.util.List;
 
// ============================================================
//  DTOs — Data Transfer Objects
//  Separam o contrato da API das entidades do banco.
//  Request = dado que ENTRA | Response = dado que SAI
// ============================================================
 
// --- SETOR ---
 
public class Dtos {
 
    // Entrada para criar setor
    
 
    // Saída de setor (sem expor funcionários para evitar loop)
    public record SetorResponse(
            Long id,
            String nome,
            String descricao
    ) {}
 
    // Setor com lista de funcionários (para a consulta especial)
    public record SetorComFuncionariosResponse(
            Long id,
            String nome,
            String descricao,
            List<FuncionarioResumoResponse> funcionarios
    ) {}
 
    // --- FUNCIONARIO ---
 
    public record CreateFuncionarioRequest(
            @NotBlank(message = "Nome é obrigatório") String nome,
            @NotBlank(message = "Email é obrigatório") String email,
            String cargo,
            @NotNull(message = "Setor é obrigatório") Long setorId
    ) {}
 
    public record FuncionarioResponse(
            Long id,
            String nome,
            String email,
            String cargo,
            SetorResponse setor
    ) {}
 
    // Resumo simples (usado dentro de outras respostas para não gerar loop)
    public record FuncionarioResumoResponse(
            Long id,
            String nome,
            String cargo
    ) {}
 
    // --- PROJETO ---
 
    public record CreateProjetoRequest(
            @NotBlank(message = "Nome é obrigatório") String nome,
            String descricao,
            @NotNull(message = "Data de início é obrigatória") LocalDate dataInicio,
            LocalDate dataFim
    ) {}
 
    public record ProjetoResponse(
            Long id,
            String nome,
            String descricao,
            LocalDate dataInicio,
            LocalDate dataFim,
            StatusProjeto status,
            List<FuncionarioResumoResponse> funcionarios
    ) {}
 
    // Resumo de projeto (usado na consulta de projetos por funcionário)
    public record ProjetoResumoResponse(
            Long id,
            String nome,
            StatusProjeto status,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {}
 
    // Requisição para vincular funcionário a projeto
    public record VincularFuncionarioRequest(
            @NotNull(message = "ID do funcionário é obrigatório") Long funcionarioId
    ) {}
}
