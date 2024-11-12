package com.example.gestao_projetos.repository;

import com.example.gestao_projetos.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
   
}