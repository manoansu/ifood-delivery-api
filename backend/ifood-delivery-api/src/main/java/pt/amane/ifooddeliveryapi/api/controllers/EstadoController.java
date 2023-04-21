package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;
import pt.amane.ifooddeliveryapi.domain.repositories.EstadoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroEstadoService;

import java.util.List;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CadastroEstadoService service;

    @GetMapping
    public List<Estado> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{estadoId}")
    public Estado findById(@PathVariable Long estadoId) {
        return service.findById(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado create(@RequestBody Estado estado) {
        return service.create(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado update(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoPersistidoBd = service.findById(estadoId);

        BeanUtils.copyProperties(estado, estadoPersistidoBd, "id");

        return service.create(estadoPersistidoBd);
    }
    @DeleteMapping(value = "/{estadoId}")
    public void delete(@PathVariable Long estadoId) {
        service.remover(estadoId);
    }
}
