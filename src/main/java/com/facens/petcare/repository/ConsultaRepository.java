package com.facens.petcare.repository;

import com.facens.petcare.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
 boolean existsByVeterinarioIdAndDataHora(Long veterinarioId, LocalDateTime dataHora);
}
