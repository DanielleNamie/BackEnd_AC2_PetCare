package com.facens.ac2.service.projeto;

import com.facens.ac2.dto.*;
import com.facens.ac2.entity.projeto.Projeto;
import com.facens.ac2.exception.ResourceNotFoundException;
import com.facens.ac2.repository.projeto.FuncionarioRepository;
import com.facens.ac2.repository.projeto.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoResponse cadastrar(CreateProjetoRequest request) {
        Projeto projeto = new Projeto(
                request.nome(),
                request.descricao(),
                request.dataInicio(),
                request.dataFim()
        );
        return ProjetoResponse.from(projetoRepository.save(projeto));
    }

    @Transactional(readOnly = true)
    public ProjetoResponse buscarPorId(Long id) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com ID: " + id));
        return ProjetoResponse.from(projeto);
    }

    @Transactional(readOnly = true)
    public List<ProjetoResponse> listarTodos() {
        return projetoRepository.findAllWithFuncionarios().stream()
                .map(ProjetoResponse::from)
                .toList();
    }

    public ProjetoResponse vincularFuncionario(Long projetoId, Long funcionarioId) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com ID: " + projetoId));

        var funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + funcionarioId));

        if (!projeto.getFuncionarios().contains(funcionario)) {
            projeto.getFuncionarios().add(funcionario);
        }

        return ProjetoResponse.from(projetoRepository.save(projeto));
    }

}
