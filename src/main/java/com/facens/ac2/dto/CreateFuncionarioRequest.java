package com.facens.ac2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFuncionarioRequest(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Email é obrigatório") String email,
        String cargo,
        @NotNull(message = "Setor é obrigatório") Long setorId
) {}