package com.facens.ac2.controller.projeto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facens.ac2.dto.CreateSetorRequest;
import com.facens.ac2.dto.SetorResponse;
import com.facens.ac2.service.projeto.SetorService;

import java.util.List;

@RestController
@RequestMapping("/api/setores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SetorController {

    private final SetorService setorService;

    @PostMapping
    
    public ResponseEntity<SetorResponse> cadastrar(@RequestBody CreateSetorRequest request) {
        SetorResponse response = setorService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(setorService.buscarPorId(id)); 
    }

    @GetMapping
    public ResponseEntity<List<SetorResponse>> listarTodos() {
        return ResponseEntity.ok(setorService.listarTodos());
    }
}
