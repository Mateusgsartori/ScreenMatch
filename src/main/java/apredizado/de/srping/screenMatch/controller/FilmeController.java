package apredizado.de.srping.screenMatch.controller;

import apredizado.de.srping.screenMatch.domain.filme.DadosAlteracaoFilmes;
import apredizado.de.srping.screenMatch.domain.filme.DadosCadstroFilmes;
import apredizado.de.srping.screenMatch.domain.filme.Filme;
import apredizado.de.srping.screenMatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

@Autowired
private FilmeRepository repository;

    @GetMapping("/formulario")
    public String carregaFormulario(Long id, Model model){
        if (id != null){
            Filme filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }
    @GetMapping()
    public String carregaListagem(Model model){
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @PostMapping()
    @Transactional
    public String cadastraFilme(DadosCadstroFilmes dados){
        Filme filme = new Filme(dados);
        repository.save(filme);
        return "redirect:/filmes";
    }

    @PutMapping()
    @Transactional
    public String alteraFilme(DadosAlteracaoFilmes dados) {
        Filme filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String removeFilme(Long id){
        repository.deleteById(id);
        return "redirect:/filmes";
    }

}
