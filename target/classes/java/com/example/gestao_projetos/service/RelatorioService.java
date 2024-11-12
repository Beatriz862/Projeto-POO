package com.example.gestao_projetos.service;

import com.example.gestao_projetos.entity.Relatorio;
import com.example.gestao_projetos.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<Relatorio> getAllRelatorios() {
        return relatorioRepository.findAll();
    }

    public Optional<Relatorio> getRelatorioById(Long id) {
        return relatorioRepository.findById(id);
    }

    public Relatorio saveRelatorio(Relatorio relatorio) {
        relatorio.setDataCriacao(LocalDate.now());
        return relatorioRepository.save(relatorio);
    }

    public void deleteRelatorio(Long id) {
        relatorioRepository.deleteById(id);
    }
}
