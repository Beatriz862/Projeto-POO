package com.example.gestao_projetos.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina; 

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;

    @ManyToMany
    @JoinTable(
        name = "projeto_membro",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "membro_id"))
    private List<Mebros> membros;

    // Construtores
    public Projeto() {
    }

    public Projeto(String nome, String descricao, Disciplina disciplina) {
        this.nome = nome;
        this.descricao = descricao;
        this.disciplina = disciplina;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<Mebros> getMembros() {
        return membros;
    }

    public void setMembros(List<Mebros> membros) {
        this.membros = membros;
    }
}