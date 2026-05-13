package com.facens.petcare.controller;

import com.facens.petcare.dto.*;
import com.facens.petcare.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponse> cadastrar(@RequestBody CreateFuncionarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.cadastrar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    // GET /api/funcionarios/1/projetos = projetos do funcionário com ID 1
    @GetMapping("/{id}/projetos")
    public ResponseEntity<List<ProjetoResponse>> buscarProjetosPorFuncionario(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarProjetosPorFuncionario(id));
    }
}
