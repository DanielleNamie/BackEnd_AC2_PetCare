package com.facens.petcare.dto;

import jakarta.validation.constraints.NotNull;

public record VincularFuncionarioRequest(
        @NotNull(message = "ID do funcionário é obrigatório") Long funcionarioId
) {}