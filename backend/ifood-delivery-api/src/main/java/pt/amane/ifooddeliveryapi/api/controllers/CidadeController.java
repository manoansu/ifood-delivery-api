package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CidadeRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CidadeRepository repository;

    @GetMapping
    public List<Cidade> listar() {
        return repository.listar();
    }

    @GetMapping(value = "/{cozinhaId}")
    public Cidade buscar(@PathVariable Long cozinhaId) {
        return repository.buscar(cozinhaId);
    }
}
