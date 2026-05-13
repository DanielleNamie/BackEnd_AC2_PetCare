package com.facens.petcare.repository;

import com.facens.petcare.entity.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
 
    // Spring Data gera a query automaticamente pelo nome do método
    boolean existsByEmail(String email);
 
    Optional<Funcionario> findByEmail(String email);
 
    List<Funcionario> findBySetorId(Long setorId);
}
