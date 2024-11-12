package com.example.gestao_projetos.controller;

import com.example.gestao_projetos.entity.Relatorio;
import com.example.gestao_projetos.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public List<Relatorio> listarRelatorios() {
        return relatorioService.getAllRelatorios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarRelatorio(@PathVariable Long id) {
        return relatorioService.getRelatorioById(id)
                .map(relatorio -> ResponseEntity.ok().body(relatorio))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Relatorio criarRelatorio(@RequestBody Relatorio relatorio) {
        return relatorioService.saveRelatorio(relatorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable Long id) {
        relatorioService.deleteRelatorio(id);
        return ResponseEntity.noContent().build();
    }
}
