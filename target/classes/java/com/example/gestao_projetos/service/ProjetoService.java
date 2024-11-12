package com.example.gestao_projetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.gestao_projetos.entity.Projeto;
import com.example.gestao_projetos.repository.ProjetoRepository;
import com.example.gestao_projetos.service.ProjetoService;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto salvar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Long id) {
        projetoRepository.deleteById(id);
    }
}