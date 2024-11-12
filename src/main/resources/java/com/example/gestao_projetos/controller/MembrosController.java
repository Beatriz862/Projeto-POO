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

import com.example.gestao_projetos.dto.RequisicaoFormMebros;
import com.example.gestao_projetos.entity.Mebros;
import com.example.gestao_projetos.entity.StatusMebros;
import com.example.gestao_projetos.repository.MebrosRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/mebros")
public class MebrosController {

    @Autowired
    private MebrosRepository mebrosRepository;


    public String handleError() {
        return "error"; // Um template de erro que você criou.
    }
    @GetMapping("")
    public ModelAndView index() {
       java.util.List<Mebros> mebros = this.mebrosRepository.findAll();
       System.err.println("nsnsnjsjshjh");
        ModelAndView mv = new ModelAndView("mebros/index");
        mv.addObject("mebros", mebros);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormMebros requisicao) {
        ModelAndView mv = new ModelAndView("mebros/new");
        mv.addObject("listaStatusMebros", StatusMebros.values());

        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormMebros requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");

            ModelAndView mv = new ModelAndView("mebros/new");
            mv.addObject("listaStatusMebros", StatusMebros.values());
            return mv;
        }
        else {
            Mebros mebros = requisicao.toMebros();
            this.mebrosRepository.save(mebros);

            return new ModelAndView("redirect:/mebros");
        }
    }


    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Mebros> optional = this.mebrosRepository.findById(id);

        if (optional.isPresent()) {
            Mebros mebros = optional.get();

            ModelAndView mv = new ModelAndView("mebros/show");
            mv.addObject("mebros", mebros);

            return mv;
        }
        // não achou um registro na tabela Membros com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Membros DE ID "+ id + " $$$$$$$$$$$");
            return this.retornaErroMebros("SHOW ERROR: Membro #" + id + " não encontrado no banco!");
        }
    }


    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormMebros requisicao) {
        Optional<Mebros> optional = this.mebrosRepository.findById(id);

        if (optional.isPresent()) {
            Mebros mebros = optional.get();
            requisicao.fromMebros(mebros);

            ModelAndView mv = new ModelAndView("mebros/edit");
            mv.addObject("mebrosId", mebros.getId());
            mv.addObject("listaStatusmebros", StatusMebros.values());

            return mv;

        }
        // não achou um registro na tabela Membros com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O MEMBRO DE ID "+ id + " $$$$$$$$$$$");
            return this.retornaErroMebros("EDIT ERROR: MEMBRO #" + id + " não encontrado no banco!");
        }
    }


    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormMebros requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("mebros/edit");
            mv.addObject("mebrosId", id);
            mv.addObject("listaStatusMebros", StatusMebros.values());
            return mv;
        }
        else {
            Optional<Mebros> optional = this.mebrosRepository.findById(id);

            if (optional.isPresent()) {
                Mebros mebros = requisicao.toMebros();
                this.mebrosRepository.save(mebros);

                return new ModelAndView("redirect:/mebros/" + mebros.getId());
            }
            // não achou um registro na tabela Professor com o id informado
            else {
                System.out.println("############ NÃO ACHOU O Membros DE ID "+ id + " ############");
                return this.retornaErroMebros("UPDATE ERROR: MEMBROS #" + id + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/mebros");

        try {
            this.mebrosRepository.deleteById(id);
            mv.addObject("mensagem", "MEMBROS #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroMebros("DELETE ERROR: MEMBRO #" + id + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroMebros(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/mebros");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
    
}