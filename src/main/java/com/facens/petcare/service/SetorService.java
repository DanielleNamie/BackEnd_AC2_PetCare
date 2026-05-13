package com.facens.petcare.service;

import com.facens.petcare.dto.*;
import com.facens.petcare.entity.Setor;
import com.facens.petcare.exception.BusinessException;
import com.facens.petcare.exception.ResourceNotFoundException;
import com.facens.petcare.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service: registra esta classe no container Spring como componente de negócio
// @Transactional: garante que as operações de banco sejam atômicas
@Service
@Transactional
@RequiredArgsConstructor
public class SetorService {

    // Injeção pelo construtor via @RequiredArgsConstructor do Lombok
    private final SetorRepository setorRepository;

    // ---- REGRA DE NEGÓCIO: não permite setor com nome duplicado ----
    public SetorResponse cadastrar(CreateSetorRequest request) {
        if (setorRepository.findByNomeIgnoreCase(request.nome()).isPresent()) {
            throw new BusinessException("Já existe um setor com o nome: " + request.nome());
        }
        Setor setor = new Setor(request.nome(), request.descricao());
        return toResponse(setorRepository.save(setor));
    }

    @Transactional(readOnly = true)
    public List<SetorResponse> listarTodos() {
        return setorRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public SetorResponse buscarPorId(Long id) {
        return toResponse(buscarOuLancar(id));
    }

    // Busca setor com funcionários carregados
    @Transactional(readOnly = true)
    public SetorComFuncionariosResponse buscarComFuncionarios(Long id) {
        Setor setor = setorRepository.findByIdWithFuncionarios(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado: " + id));
        return toComFuncionariosResponse(setor);
    }

    @Transactional(readOnly = true)
    public List<SetorComFuncionariosResponse> listarComFuncionarios() {
        return setorRepository.findAllWithFuncionarios().stream()
                .map(this::toComFuncionariosResponse)
                .toList();
    }

    // ---- Métodos privados de apoio ----

    private Setor buscarOuLancar(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado: " + id));
    }

    // Converte entidade → DTO de resposta (mapeia manualmente, sem biblioteca extra)
    private SetorResponse toResponse(Setor s) {
        return new SetorResponse(s.getId(), s.getNome(), s.getDescricao());
    }

    private SetorComFuncionariosResponse toComFuncionariosResponse(Setor s) {
        List<FuncionarioResumoResponse> funcionarios = s.getFuncionarios().stream()
                .map(f -> new FuncionarioResumoResponse(f.getId(), f.getNome(), f.getCargo()))
                .toList();
        return new SetorComFuncionariosResponse(s.getId(), s.getNome(), s.getDescricao(), funcionarios);
    }
}
