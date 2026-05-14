package com.facens.ac2.repository.projeto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facens.ac2.entity.projeto.Setor;
 
@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
 
    Optional<Setor> findByNomeIgnoreCase(String nome);
 
    @Query("SELECT DISTINCT s FROM Setor s LEFT JOIN FETCH s.funcionarios WHERE s.id = :id")
    Optional<Setor> findByIdWithFuncionarios(@Param("id") Long id);
 
    @Query("SELECT DISTINCT s FROM Setor s LEFT JOIN FETCH s.funcionarios")
    List<Setor> findAllWithFuncionarios();
}
