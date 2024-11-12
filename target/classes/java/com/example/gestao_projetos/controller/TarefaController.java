package com.example.gestao_projetos.controller;

import com.example.gestao_projetos.entity.Tarefa;
import com.example.gestao_projetos.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaService.getAllTarefas();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.getTarefaById(id);
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.saveTarefa(tarefa);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projeto/{projetoId}")
    public List<Tarefa> getTarefasByProjetoId(@PathVariable Long projetoId) {
        return tarefaService.getTarefasByProjetoId(projetoId);
    }

    @GetMapping("/membro/{membroId}")
    public List<Tarefa> getTarefasByMembroId(@PathVariable Long membroId) {
        return tarefaService.getTarefasByMembroId(membroId);
    }

    @GetMapping("/subtarefas/{tarefaPrincipalId}")
    public List<Tarefa> getSubtarefasByTarefaPrincipalId(@PathVariable Long tarefaPrincipalId) {
        return tarefaService.getSubtarefasByTarefaPrincipalId(tarefaPrincipalId);
    }
}