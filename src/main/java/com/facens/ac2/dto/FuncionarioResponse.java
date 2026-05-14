package com.facens.ac2.dto;

import lombok.Builder;

@Builder
public record FuncionarioResponse(
        Long id,
        String nome,
        String email,
        String cargo,
        SetorResponse setor
) {}