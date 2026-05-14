package com.facens.ac2.service.projeto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facens.ac2.dto.CreateFuncionarioRequest;
import com.facens.ac2.dto.FuncionarioResponse;
import com.facens.ac2.dto.ProjetoResponse;
import com.facens.ac2.dto.SetorResponse;
import com.facens.ac2.entity.projeto.Funcionario;
import com.facens.ac2.entity.projeto.Setor;
import com.facens.ac2.exception.BusinessException;
import com.facens.ac2.exception.ResourceNotFoundException;
import com.facens.ac2.repository.projeto.FuncionarioRepository;
import com.facens.ac2.repository.projeto.ProjetoRepository;
import com.facens.ac2.repository.projeto.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    private final ProjetoRepository projetoRepository;

    @Transactional
    public FuncionarioResponse cadastrar(CreateFuncionarioRequest request) {
        if (funcionarioRepository.existsByEmail(request.email())) {
            throw new BusinessException("E-mail já cadastrado: " + request.email());
        }

        if (request.nome() == null || request.nome().isBlank()) {
            throw new BusinessException("Nome do funcionário é obrigatório");
        }

        Setor setor = setorRepository.findById(request.setorId())
        .orElseThrow(() -> new ResourceNotFoundException(
                "Setor não encontrado com ID: " + request.setorId()));

        Funcionario funcionario = Funcionario.builder()
                .nome(request.nome())
                .email(request.email())
                .cargo(request.cargo())
                .setor(setor) 
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
        if (!funcionarioRepository.existsById(funcionarioId)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com ID: " + funcionarioId);
        }
        return projetoRepository.findByFuncionarioId(funcionarioId).stream()
                .map(ProjetoResponse::from)
                .toList();
    }

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
