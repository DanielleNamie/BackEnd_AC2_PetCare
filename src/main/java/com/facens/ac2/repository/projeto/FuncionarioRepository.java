package com.facens.ac2.repository.projeto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facens.ac2.entity.projeto.Funcionario;
 
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
 
    boolean existsByEmail(String email);
 
    Optional<Funcionario> findByEmail(String email);
 
    List<Funcionario> findBySetorId(Long setorId);
}
