package pt.amane.ifooddeliveryapi.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.EstadoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.EstadoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.EstadoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.EstadoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;
import pt.amane.ifooddeliveryapi.domain.repositories.EstadoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroEstadoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CadastroEstadoService service;

    @Autowired
    private EstadoDTOAssembler estadoDTOAssembler;

    @Autowired
    private EstadoInputDataDisassembler estadoInputDataDisassembler;

    @GetMapping
    public List<EstadoDTO> findAll() {
        List<Estado> todosEstados = repository.findAll();

        return estadoDTOAssembler.toCollectionModel(todosEstados);
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO findById(@PathVariable Long estadoId) {
        Estado estado = service.findById(estadoId);

        return estadoDTOAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO create(@RequestBody @Valid EstadoInputData estadoInputData) {
        Estado estado = estadoInputDataDisassembler.toDomainObject(estadoInputData);

        estado = service.create(estado);

        return estadoDTOAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO upadate(@PathVariable Long estadoId,
                                 @RequestBody @Valid EstadoInputData estadoInputData) {
        Estado estadoAtual = service.findById(estadoId);

        estadoInputDataDisassembler.copyToDomainObject(estadoInputData, estadoAtual);

        estadoAtual = service.create(estadoAtual);

        return estadoDTOAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        service.delete(estadoId);
    }

}
