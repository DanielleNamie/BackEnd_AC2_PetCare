package com.facens.petcare.dto;

import lombok.Builder;

@Builder
public record FuncionarioResponse(
        Long id,
        String nome,
        String email,
        String cargo,
        SetorResponse setor
) {}