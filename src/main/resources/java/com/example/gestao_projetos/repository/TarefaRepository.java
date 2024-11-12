package com.example.gestao_projetos.repository;

import com.example.gestao_projetos.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    
    List<Tarefa> findByProjetoId(Long projetoId);

    
    List<Tarefa> findByMembroId(Long membroId);

    
    List<Tarefa> findByTarefaPrincipalId(Long tarefaPrincipalId);
}