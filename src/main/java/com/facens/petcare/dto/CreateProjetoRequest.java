package com.facens.petcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateProjetoRequest(
        @NotBlank(message = "Nome é obrigatório") String nome,
        String descricao,
        @NotNull(message = "Data de início é obrigatória") LocalDate dataInicio,
        LocalDate dataFim
) {}