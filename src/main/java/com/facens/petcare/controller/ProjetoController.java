package com.facens.petcare.controller;

import com.facens.petcare.dto.*;
import com.facens.petcare.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<ProjetoResponse> cadastrar(@Valid @RequestBody CreateProjetoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponse>> listarTodos() {
        return ResponseEntity.ok(projetoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.buscarPorId(id));
    }

    @PostMapping("/{projetoId}/funcionarios")
    public ResponseEntity<ProjetoResponse> vincularFuncionario(@PathVariable Long projetoId,
                                                               @RequestBody VincularFuncionarioRequest request) {
        return ResponseEntity.ok(projetoService.vincularFuncionario(projetoId, request.funcionarioId()));
    }
}
