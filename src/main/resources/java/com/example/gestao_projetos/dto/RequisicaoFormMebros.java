package com.example.gestao_projetos.dto;


import com.example.gestao_projetos.entity.Mebros;
import com.example.gestao_projetos.entity.StatusMebros;

import jakarta.validation.constraints.NotBlank;

public class RequisicaoFormMebros {
    @NotBlank
    @jakarta.validation.constraints.NotNull
    private String nome; // em caso de erro, NotBlank.requisicaoNovoProfessor.nome
    @NotBlank
    @jakarta.validation.constraints.NotNull
    private String funcao;
    private StatusMebros statusMebros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


    public StatusMebros getStatusMebros( ){
        return statusMebros;
    }


    public Mebros toMebros() {
        Mebros mebros = new Mebros();
        mebros.setNome(this.nome);
        mebros.setFuncao(this.funcao);
        mebros.setStatusMebros(this.statusMebros); // Usando um status equivalente.
    
        return mebros;
    }
    
    public void fromMebros(Mebros mebros) {
        this.nome = mebros.getNome();
        this.funcao = mebros.getFuncao();
        this.statusMebros = mebros.getStatusMebros(); // Adicionei a obtenção do status.
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
