package com.facens.petcare.repository;

import com.facens.petcare.entity.Setor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
 
    Optional<Setor> findByNomeIgnoreCase(String nome);
 
    // JOIN FETCH carrega os funcionários numa única query (evita problema N+1)
    @Query("SELECT DISTINCT s FROM Setor s LEFT JOIN FETCH s.funcionarios WHERE s.id = :id")
    Optional<Setor> findByIdWithFuncionarios(@Param("id") Long id);
 
    @Query("SELECT DISTINCT s FROM Setor s LEFT JOIN FETCH s.funcionarios")
    List<Setor> findAllWithFuncionarios();
}
