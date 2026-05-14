package com.facens.ac2.service.projeto;

import com.facens.ac2.dto.*;
import com.facens.ac2.entity.projeto.Setor;
import com.facens.ac2.exception.BusinessException;
import com.facens.ac2.exception.ResourceNotFoundException;
import com.facens.ac2.repository.projeto.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SetorService {

    private final SetorRepository setorRepository;

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

    private Setor buscarOuLancar(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado: " + id));
    }

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
