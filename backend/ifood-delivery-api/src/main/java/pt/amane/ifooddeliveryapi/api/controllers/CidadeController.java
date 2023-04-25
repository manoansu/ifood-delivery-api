package pt.amane.ifooddeliveryapi.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.CidadeDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.CidadeInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.CidadeDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.CidadeInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.exception.EstadoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;
import pt.amane.ifooddeliveryapi.domain.repositories.CidadeRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroCidadeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private CadastroCidadeService service;

    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;

    @Autowired
    private CidadeInputDataDisassembler cidadeInputDataDisassembler;

    @GetMapping
    public List<CidadeDTO> findAll() {
        List<Cidade> todasCidades = repository.findAll();

        return cidadeDTOAssembler.toCollectionModel(todasCidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO findById(@PathVariable Long cidadeId) {
        Cidade cidade = service.findById(cidadeId);

        return cidadeDTOAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO create(@RequestBody @Valid CidadeInputData cidadeInputData) {
        try {
            Cidade cidade = cidadeInputDataDisassembler.toDomainObject(cidadeInputData);

            cidade = service.create(cidade);

            return cidadeDTOAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO upadate(@PathVariable Long cidadeId,
                             @RequestBody @Valid CidadeInputData cidadeInputData) {
        try {
            Cidade cidadeAtual = service.findById(cidadeId);

            cidadeInputDataDisassembler.copyToDomainObject(cidadeInputData, cidadeAtual);

            cidadeAtual = service.create(cidadeAtual);

            return cidadeDTOAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cidadeId) {
        service.delete(cidadeId);
    }
}

