package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.EstadoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroEstadoService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Estado> findById(@PathVariable Long estadoId) {

        Optional<Estado> estadoOptional = repository.findById(estadoId);

        if (estadoOptional.isPresent()) {
            return ResponseEntity.ok(estadoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado create(@RequestBody Estado estado) {
        return service.create(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> update(@PathVariable Long estadoId,
                                         @RequestBody Estado estado) {
        Optional<Estado> estadoPersistidoBd = repository.findById(estadoId);

        if (estadoPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(estado, estadoPersistidoBd.get(), "id");

            Estado estadoSalva = service.create(estadoPersistidoBd.get());
            return ResponseEntity.ok(estadoSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{estadoId}")
    public ResponseEntity<Estado> delete(@PathVariable Long estadoId) {

        try {
            service.remover(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
