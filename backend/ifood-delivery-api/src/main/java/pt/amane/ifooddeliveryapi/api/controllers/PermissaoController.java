package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController {

    @Autowired
    private PermissaoRepository repository;

    @GetMapping
    public List<Permissao> listar() {
        return repository.listar();
    }
}
