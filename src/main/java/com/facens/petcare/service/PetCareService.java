package com.facens.petcare.service;

import com.facens.petcare.entity.*;
import com.facens.petcare.exception.BusinessException;
import com.facens.petcare.exception.ResourceNotFoundException;
import com.facens.petcare.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PetCareService {
    private final TutorRepository tutorRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ConsultaRepository consultaRepository;

    public Tutor cadastrarTutor(Tutor tutor){return tutorRepository.save(tutor);}    
    public Veterinario cadastrarVeterinario(Veterinario veterinario){return veterinarioRepository.save(veterinario);}    

    public Animal cadastrarAnimal(Animal animal, Long tutorId){
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    public Consulta agendarConsulta(Long animalId, Long veterinarioId, LocalDateTime dataHora, String especialidade, String observacoes){
        if(consultaRepository.existsByVeterinarioIdAndDataHora(veterinarioId, dataHora)){
            throw new BusinessException("Já existe consulta nesse horário");
        }

        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        if(!veterinario.getEspecialidade().equalsIgnoreCase(especialidade)){
            throw new BusinessException("Veterinário não atende essa especialidade");
        }

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));

        Consulta consulta = Consulta.builder()
                .animal(animal)
                .veterinario(veterinario)
                .dataHora(dataHora)
                .observacoes(observacoes)
                .build();

        return consultaRepository.save(consulta);
    }
}
