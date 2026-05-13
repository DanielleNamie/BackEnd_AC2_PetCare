package com.facens.petcare.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSetorRequest(
        @NotBlank(message = "Nome é obrigatório") String nome,
        String descricao
) {}