package com.example.gestao_projetos.service;

import com.example.gestao_projetos.entity.Tarefa;
import com.example.gestao_projetos.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;


    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }


    public Optional<Tarefa> getTarefaById(Long id) {
        return tarefaRepository.findById(id);
    }
    
    public Tarefa saveTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    
    public void deleteTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
    
    public List<Tarefa> getTarefasByProjetoId(Long projetoId) {
        return tarefaRepository.findByProjetoId(projetoId);
    }

    public List<Tarefa> getTarefasByMembroId(Long membroId) {
        return tarefaRepository.findByMembroId(membroId);
    }

    public List<Tarefa> getSubtarefasByTarefaPrincipalId(Long tarefaPrincipalId) {
        return tarefaRepository.findByTarefaPrincipalId(tarefaPrincipalId);
    }
}