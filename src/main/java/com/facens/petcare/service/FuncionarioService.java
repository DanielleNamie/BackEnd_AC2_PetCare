package com.facens.petcare.service;

import com.facens.petcare.dto.*;
import com.facens.petcare.entity.Funcionario;
import com.facens.petcare.entity.Setor;
import com.facens.petcare.exception.BusinessException;
import com.facens.petcare.exception.ResourceNotFoundException;
import com.facens.petcare.repository.FuncionarioRepository;
import com.facens.petcare.repository.ProjetoRepository;
import com.facens.petcare.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    private final ProjetoRepository projetoRepository;

    @Transactional
    public FuncionarioResponse cadastrar(CreateFuncionarioRequest request) {
        // REGRA DE NEGÓCIO: email único por funcionário
        if (funcionarioRepository.existsByEmail(request.email())) {
            throw new BusinessException("E-mail já cadastrado: " + request.email());
        }

        // VALIDAÇÃO: nome e email são obrigatórios (reforço manual além da annotation @NotBlank)
        if (request.nome() == null || request.nome().isBlank()) {
            throw new BusinessException("Nome do funcionário é obrigatório");
        }

        // Busca o setor se o ID foi informado
        Setor setor = null;
        if (request.setorId() != null) {
            // Se informou setorId mas não existe, lança erro
            setor = setorRepository.findById(request.setorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado com ID: " + request.setorId()));
        }

        Funcionario funcionario = Funcionario.builder()
                .nome(request.nome())
                .email(request.email())
                .cargo(request.cargo())
                .setor(setor) // pode ser null (funcionário sem setor)
                .build();

        Funcionario salvo = funcionarioRepository.save(funcionario);
        return toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse buscarPorId(Long id) {
        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));
        return toResponse(f);
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProjetoResponse> buscarProjetosPorFuncionario(Long funcionarioId) {
        // Verifica se o funcionário existe antes de buscar projetos
        if (!funcionarioRepository.existsById(funcionarioId)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com ID: " + funcionarioId);
        }
        return projetoRepository.findByFuncionarioId(funcionarioId).stream()
                .map(ProjetoResponse::from)
                .toList();
    }

    // Método privado de conversão (evita repetição de código)
    private FuncionarioResponse toResponse(Funcionario f) {
        return FuncionarioResponse.builder()
                .id(f.getId())
                .nome(f.getNome())
                .email(f.getEmail())
                .cargo(f.getCargo())
                .setor(f.getSetor() != null ? SetorResponse.from(f.getSetor()) : null)
                .build();
    }
}
