package com.facens.petcare.controller;

import com.facens.petcare.dto.CreateSetorRequest;
import com.facens.petcare.dto.SetorResponse;
import com.facens.petcare.service.SetorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController: combina @Controller + @ResponseBody
//   Significa que os métodos retornam JSON automaticamente (não HTML)
@RestController
// @RequestMapping: prefixo de URL para todos os endpoints desta classe
@RequestMapping("/api/setores")
@RequiredArgsConstructor
// @CrossOrigin: permite requisições do frontend (HTML/JS em outra porta)
@CrossOrigin(origins = "*")
public class SetorController {

    private final SetorService setorService;

    // @PostMapping: responde a requisições HTTP POST em /api/setores
    // POST = criação de recurso
    @PostMapping
    // ResponseEntity: permite controlar o status HTTP da resposta
    // @RequestBody: lê o JSON da requisição e converte para SetorRequest
    public ResponseEntity<SetorResponse> cadastrar(@RequestBody CreateSetorRequest request) {
        SetorResponse response = setorService.cadastrar(request);
        // 201 Created = recurso criado com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // @GetMapping("/{id}"): GET em /api/setores/1 (por exemplo)
    // @PathVariable: captura o {id} da URL
    @GetMapping("/{id}")
    public ResponseEntity<SetorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(setorService.buscarPorId(id)); // 200 OK
    }

    // GET em /api/setores = lista todos os setores com seus funcionários
    @GetMapping
    public ResponseEntity<List<SetorResponse>> listarTodos() {
        return ResponseEntity.ok(setorService.listarTodos());
    }
}
