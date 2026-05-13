package com.facens.petcare.dto;

import java.util.List;

public record SetorComFuncionariosResponse(
        Long id,
        String nome,
        String descricao,
        List<FuncionarioResumoResponse> funcionarios
) {}