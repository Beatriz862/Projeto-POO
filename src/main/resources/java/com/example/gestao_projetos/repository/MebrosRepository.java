package com.example.gestao_projetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestao_projetos.entity.Mebros;

@Repository
public interface MebrosRepository extends JpaRepository<Mebros, Long>{

} 