package com.example.gestao_projetos.controller;

import com.example.gestao_projetos.entity.Disciplina;
import com.example.gestao_projetos.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @PostMapping
    public Disciplina criarDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarDisciplina(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(disciplina -> ResponseEntity.ok().body(disciplina))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        return disciplinaRepository.findById(id)
                .map(existingDisciplina -> {
                    existingDisciplina.setNome(disciplina.getNome());
                    existingDisciplina.setDescricao(disciplina.getDescricao());
                    existingDisciplina.setCargaHoraria(disciplina.getCargaHoraria());
                    Disciplina updatedDisciplina = disciplinaRepository.save(existingDisciplina);
                    return ResponseEntity.ok().body(updatedDisciplina);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(disciplina -> {
                    disciplinaRepository.delete(disciplina);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}