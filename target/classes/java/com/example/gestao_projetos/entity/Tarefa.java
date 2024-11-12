package com.example.gestao_projetos.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;



@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(nullable = false)
    private Boolean concluida = false;

    
    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    
    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Mebros membro;

    
    @OneToMany(mappedBy = "tarefaPrincipal", cascade = CascadeType.ALL)
    private List<Tarefa> subtarefas;

    
    @ManyToOne
    @JoinColumn(name = "tarefa_principal_id")
    private Tarefa tarefaPrincipal;

   
    @ManyToMany
    @JoinTable(
            name = "tarefa_disciplinas",
            joinColumns = @JoinColumn(name = "tarefa_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;

    

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

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Mebros getMembro() {
        return membro;
    }

    public void setMembro(Mebros Membros) {
        this.membro = membro;
    }

    public List<Tarefa> getSubtarefas() {
        return subtarefas;
    }

    public void setSubtarefas(List<Tarefa> subtarefas) {
        this.subtarefas = subtarefas;
    }

    public Tarefa getTarefaPrincipal() {
        return tarefaPrincipal;
    }

    public void setTarefaPrincipal(Tarefa tarefaPrincipal) {
        this.tarefaPrincipal = tarefaPrincipal;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}