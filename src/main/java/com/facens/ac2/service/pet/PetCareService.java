package com.facens.ac2.service.pet;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.facens.ac2.entity.pet.Animal;
import com.facens.ac2.entity.pet.Consulta;
import com.facens.ac2.entity.pet.Tutor;
import com.facens.ac2.entity.pet.Veterinario;
import com.facens.ac2.exception.BusinessException;
import com.facens.ac2.exception.ResourceNotFoundException;
import com.facens.ac2.repository.pet.AnimalRepository;
import com.facens.ac2.repository.pet.ConsultaRepository;
import com.facens.ac2.repository.pet.TutorRepository;
import com.facens.ac2.repository.pet.VeterinarioRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetCareService {
    private final TutorRepository tutorRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ConsultaRepository consultaRepository;

    public Tutor cadastrarTutor(Tutor tutor){return tutorRepository.save(tutor);}    
    public Veterinario cadastrarVeterinario(Veterinario veterinario){return veterinarioRepository.save(veterinario);}    

    public List<Tutor> listarTutores() {
    return tutorRepository.findAll();
    }

    public List<Consulta> listarConsultas() {
    return consultaRepository.findAll();
    }

    public List<Animal> listarAnimais() {
    return animalRepository.findAll();
    }

    public Animal cadastrarAnimal(Animal animal, Long tutorId){
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    public List<Veterinario> listarVeterinarios() {
    return veterinarioRepository.findAll();
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
