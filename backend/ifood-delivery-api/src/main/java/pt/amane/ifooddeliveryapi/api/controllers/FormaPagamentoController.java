package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroFormaPagamentoService;

import java.util.List;

@RestController
@RequestMapping(value = "/formapagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository repository;

    @Autowired
    private CadastroFormaPagamentoService service;

    @GetMapping
    public List<FormaPagamento> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{formapagamentoId}")
    public FormaPagamento findById(@PathVariable Long formapagamentoId) {
        return service.findById(formapagamentoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento create(@RequestBody FormaPagamento formaPagamento) {
        return service.create(formaPagamento);
    }

    @PutMapping("/{formapagamentoId}")
    public FormaPagamento update(@PathVariable Long formapagamentoId, @RequestBody Cidade cidade) {
        FormaPagamento formapagamentoPersistidoBd = service.findById(formapagamentoId);

        BeanUtils.copyProperties(cidade, formapagamentoPersistidoBd, "id");

        return service.create(formapagamentoPersistidoBd);

    }
    @DeleteMapping(value = "/{formapagamentoId}")
    public void delete(@PathVariable Long formapagamentoId) {
        service.delete(formapagamentoId);
    }
}
