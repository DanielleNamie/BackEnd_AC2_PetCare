package com.facens.petcare.controller;

import com.facens.petcare.entity.*;
import com.facens.petcare.service.PetCareService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/petcare")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PetCareController {

    private final PetCareService petCareService;

    @PostMapping("/tutores")
    public Tutor cadastrarTutor(@RequestBody Tutor tutor){
        return petCareService.cadastrarTutor(tutor);
    }

    @PostMapping("/veterinarios")
    public Veterinario cadastrarVeterinario(@RequestBody Veterinario veterinario){
        return petCareService.cadastrarVeterinario(veterinario);
    }

    @PostMapping("/animais/{tutorId}")
    public Animal cadastrarAnimal(@RequestBody Animal animal, @PathVariable Long tutorId){
        return petCareService.cadastrarAnimal(animal, tutorId);
    }

    @PostMapping("/consultas")
    public Consulta agendarConsulta(@RequestBody ConsultaRequest request){
        return petCareService.agendarConsulta(request.animalId, request.veterinarioId,
                request.dataHora, request.especialidade, request.observacoes);
    }

    @Data
    public static class ConsultaRequest{
        private Long animalId;
        private Long veterinarioId;
        private LocalDateTime dataHora;
        private String especialidade;
        private String observacoes;
    }
}
