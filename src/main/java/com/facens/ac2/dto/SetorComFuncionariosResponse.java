package com.facens.ac2.dto;

import java.util.List;

public record SetorComFuncionariosResponse(
        Long id,
        String nome,
        String descricao,
        List<FuncionarioResumoResponse> funcionarios
) {}