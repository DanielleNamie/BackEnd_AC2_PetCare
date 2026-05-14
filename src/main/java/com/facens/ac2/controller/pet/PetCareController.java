package com.facens.ac2.controller.pet;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.facens.ac2.entity.pet.Animal;
import com.facens.ac2.entity.pet.Consulta;
import com.facens.ac2.entity.pet.Tutor;
import com.facens.ac2.entity.pet.Veterinario;
import com.facens.ac2.service.pet.PetCareService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    @GetMapping("/tutores")
    public List<Tutor> listarTutores() {
    return petCareService.listarTutores();
    }

    @PostMapping("/veterinarios")
    public Veterinario cadastrarVeterinario(@RequestBody Veterinario veterinario){
        return petCareService.cadastrarVeterinario(veterinario);
    }

    @GetMapping("/veterinarios")
    public List<Veterinario> listarVeterinarios() {
    return petCareService.listarVeterinarios();
    }


    @PostMapping("/animais/{tutorId}")
    public Animal cadastrarAnimal(@RequestBody Animal animal, @PathVariable Long tutorId){
        return petCareService.cadastrarAnimal(animal, tutorId);
    }
    @GetMapping("/animais")
    public List<Animal> listarAnimais() {
    return petCareService.listarAnimais();
    }

    @PostMapping("/consultas")
    public Consulta agendarConsulta(@RequestBody ConsultaRequest request){
        return petCareService.agendarConsulta(request.animalId, request.veterinarioId,
                request.dataHora, request.especialidade, request.observacoes);
    }
    @GetMapping("/consultas")
    public List<Consulta> listarConsultas() {
    return petCareService.listarConsultas();
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
