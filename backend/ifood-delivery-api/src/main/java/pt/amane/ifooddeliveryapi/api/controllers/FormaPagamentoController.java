package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.FormaPagamentoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.FormaPagamentoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.FormaPagamentoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.FormaPagamentoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroFormaPagamentoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/formapagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository repository;

    @Autowired
    private CadastroFormaPagamentoService service;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private FormaPagamentoInputDataDisassembler formaPagamentoInputDataDisassembler;

    @GetMapping
    public List<FormaPagamentoDTO> findAll() {
        List<FormaPagamento> formaPagamentos = repository.findAll();

        return formaPagamentoDTOAssembler.toCollectionModel(formaPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO findById(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = service.findById(formaPagamentoId);

        return formaPagamentoDTOAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO create(@RequestBody @Valid FormaPagamentoInputData formaPagamentoInputData) {
        FormaPagamento formaPagamento = formaPagamentoInputDataDisassembler.toDomainObject(formaPagamentoInputData);
        formaPagamento = service.create(formaPagamento);

        return formaPagamentoDTOAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO upadate(@PathVariable Long formaPagamentoId,
                              @RequestBody @Valid FormaPagamentoInputData formaPagamentoInputData) {
        FormaPagamento formaPagamento = service.findById(formaPagamentoId);
        formaPagamentoInputDataDisassembler.copyToDomainObject(formaPagamentoInputData, formaPagamento);
        formaPagamento = service.create(formaPagamento);

        return formaPagamentoDTOAssembler.toModel(formaPagamento);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formaPagamentoId) {
        service.delete(formaPagamentoId);
    }

}
