package com.facens.ac2.repository.pet;

import com.facens.ac2.entity.pet.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
 boolean existsByVeterinarioIdAndDataHora(Long veterinarioId, LocalDateTime dataHora);
}
