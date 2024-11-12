package com.example.gestao_projetos.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Mebros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String funcao;
    @Enumerated(EnumType.STRING)
    private StatusMebros statusMebros;
    public Mebros() {  }

    public Mebros(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
    }

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
     
    public StatusMebros getStatusMebros() {
        return statusMebros;
    }
    public void setStatusMebros(StatusMebros statusMebros) {
        this.statusMebros = statusMebros;
    }
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "RequisicaoNovoMebros{" +
               "nome='" + nome + '\'' +
               ", funcao='" + funcao + '\'' +
               ", statusMebros=" + statusMebros + // Incluindo o status no toString.
               '}';
    }
    
}
