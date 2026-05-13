package com.facens.petcare.dto;

import com.facens.petcare.entity.Setor;
import lombok.Builder;

@Builder
public record SetorResponse(
        Long id,
        String nome,
        String descricao
) {

    public static SetorResponse from(Setor setor) {
        return SetorResponse.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .descricao(setor.getDescricao())
                .build();
    }
}
