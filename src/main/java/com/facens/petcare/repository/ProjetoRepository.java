package com.facens.petcare.repository;

import com.facens.petcare.entity.Projeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
 
    // Busca projeto COM funcionários (evita LazyInitializationException)
    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(@Param("id") Long id);
 
    // Projetos iniciados dentro de um período
    @Query("SELECT p FROM Projeto p WHERE p.dataInicio >= :inicio AND " +
           "(p.dataFim IS NULL OR p.dataFim <= :fim)")
    List<Projeto> findByPeriodo(@Param("inicio") LocalDate inicio,
                                @Param("fim") LocalDate fim);
 
    // Todos os projetos que um funcionário participa
    @Query("SELECT DISTINCT p FROM Projeto p JOIN p.funcionarios f WHERE f.id = :funcionarioId")
    List<Projeto> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);
 
    // Lista completa com funcionários
    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios")
    List<Projeto> findAllWithFuncionarios();
}
