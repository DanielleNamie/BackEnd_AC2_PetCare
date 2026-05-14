package com.facens.ac2.repository.projeto;

import com.facens.ac2.entity.projeto.Projeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
 
    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(@Param("id") Long id);
 
    @Query("SELECT p FROM Projeto p WHERE p.dataInicio >= :inicio AND " +
           "(p.dataFim IS NULL OR p.dataFim <= :fim)")
    List<Projeto> findByPeriodo(@Param("inicio") LocalDate inicio,
                                @Param("fim") LocalDate fim);
 
    @Query("SELECT DISTINCT p FROM Projeto p JOIN p.funcionarios f WHERE f.id = :funcionarioId")
    List<Projeto> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);
 
    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios")
    List<Projeto> findAllWithFuncionarios();
}
