package com.example.gestao_projetos.service;

import com.example.gestao_projetos.entity.Disciplina;
import com.example.gestao_projetos.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina salvarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizarDisciplina(Long id, Disciplina disciplinaAtualizada) {
        return disciplinaRepository.findById(id)
                .map(disciplina -> {
                    disciplina.setNome(disciplinaAtualizada.getNome()); // Supondo que a entidade tenha um campo nome
                    disciplina.setDescricao(disciplinaAtualizada.getDescricao()); // E outro campo descrição
                    return disciplinaRepository.save(disciplina);
                })
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    public void deletarDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}