package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/testes")
public class TestController {

//    @Autowired
//    private CozinhaRepository cozinhaRepository;

//    @GetMapping(value = "/cozinhas/por-nome")
//    public List<Cozinha> listarCozinhasPorNome(@RequestParam("nome") String nome) {
//        return cozinhaRepository.listrPorNome(nome);
//    }
    }
