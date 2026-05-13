package com.facens.petcare.dto;

import com.facens.petcare.entity.Projeto.StatusProjeto;

import java.time.LocalDate;

public record ProjetoResumoResponse(
        Long id,
        String nome,
        StatusProjeto status,
        LocalDate dataInicio,
        LocalDate dataFim
) {}