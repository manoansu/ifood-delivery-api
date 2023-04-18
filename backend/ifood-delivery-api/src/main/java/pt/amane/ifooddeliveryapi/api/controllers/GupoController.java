package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.repositories.GrupoRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GupoController {

    @Autowired
    private GrupoRepository repository;

    @GetMapping
    public List<Grupo> listar() {
        return repository.listar();
    }
}
