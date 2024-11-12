package com.example.gestao_projetos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.gestao_projetos.dto.RequisicaoFormProjeto;
import com.example.gestao_projetos.entity.Projeto;
import com.example.gestao_projetos.entity.StatusProjeto;
import com.example.gestao_projetos.repository.ProjetoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    public String handleError() {
        return "error";
    }

    @GetMapping("")
    public ModelAndView index() {
        java.util.List<Projeto> projetos = this.projetoRepository.findAll();
        ModelAndView mv = new ModelAndView("projetos/index");
        mv.addObject("projetos", projetos);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProjeto requisicao) {
        ModelAndView mv = new ModelAndView("projetos/new");
        mv.addObject("listaStatusProjeto", StatusProjeto.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProjeto requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("projetos/new");
            mv.addObject("listaStatusProjeto", StatusProjeto.values());
            return mv;
        } else {
            Projeto projeto = requisicao.toProjeto();
            this.projetoRepository.save(projeto);
            return new ModelAndView("redirect:/projetos");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Projeto> optional = this.projetoRepository.findById(id);
        if (optional.isPresent()) {
            Projeto projeto = optional.get();
            ModelAndView mv = new ModelAndView("projetos/show");
            mv.addObject("projeto", projeto);
            return mv;
        } else {
            return this.retornaErroProjeto("SHOW ERROR: Projeto #" + id + " não encontrado no banco!");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProjeto requisicao) {
        Optional<Projeto> optional = this.projetoRepository.findById(id);
        if (optional.isPresent()) {
            Projeto projeto = optional.get();
            requisicao.fromProjeto(projeto);
            ModelAndView mv = new ModelAndView("projetos/edit");
            mv.addObject("projetoId", projeto.getId());
            mv.addObject("listaStatusProjeto", StatusProjeto.values());
            return mv;
        } else {
            return this.retornaErroProjeto("EDIT ERROR: Projeto #" + id + " não encontrado no banco!");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProjeto requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("projetos/edit");
            mv.addObject("projetoId", id);
            mv.addObject("listaStatusProjeto", StatusProjeto.values());
            return mv;
        } else {
            Optional<Projeto> optional = this.projetoRepository.findById(id);
            if (optional.isPresent()) {
                Projeto projeto = requisicao.toProjeto();
                projeto.setId(id); // Assegura que estamos atualizando o projeto com o ID correto
                this.projetoRepository.save(projeto);
                return new ModelAndView("redirect:/projetos/" + projeto.getId());
            } else {
                return this.retornaErroProjeto("UPDATE ERROR: Projeto #" + id + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/projetos");
        try {
            this.projetoRepository.deleteById(id);
            mv.addObject("mensagem", "Projeto #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            mv = this.retornaErroProjeto("DELETE ERROR: Projeto #" + id + " não encontrado no banco!");
        }
        return mv;
    }

    private ModelAndView retornaErroProjeto(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/projetos");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
