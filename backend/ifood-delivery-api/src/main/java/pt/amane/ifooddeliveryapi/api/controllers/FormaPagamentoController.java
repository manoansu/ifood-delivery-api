package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/formapagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository repository;

    @GetMapping
    public List<FormaPagamento> listar() {
        return repository.listar();
    }
}
