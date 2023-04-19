package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroFormaPagamentoService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<FormaPagamento> findById(@PathVariable Long formapagamentoId) {

        Optional<FormaPagamento> formaPagamentoOptional = repository.findById(formapagamentoId);

        if (formaPagamentoOptional.isPresent()) {
            return ResponseEntity.ok(formaPagamentoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento create(@RequestBody FormaPagamento formaPagamento) {
        return service.create(formaPagamento);
    }

    @PutMapping("/{formapagamentoId}")
    public ResponseEntity<FormaPagamento> update(@PathVariable Long formapagamentoId,
                                         @RequestBody Cidade cidade) {
        Optional<FormaPagamento> formapagamentoPersistidoBd = repository.findById(formapagamentoId);

        if (formapagamentoPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(cidade, formapagamentoPersistidoBd.get(), "id");

            FormaPagamento formapagamentoSalva = repository.save(formapagamentoPersistidoBd.get());
            return ResponseEntity.ok(formapagamentoSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{formapagamentoId}")
    public ResponseEntity<FormaPagamento> delete(@PathVariable Long formapagamentoId) {

        try {
            service.delete(formapagamentoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
