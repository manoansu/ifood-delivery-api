package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroCozinhaService;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private CadastroCozinhaService service;

    @GetMapping
    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{cozinhaId}")
    public Cozinha findById(@PathVariable Long cozinhaId) {
       return service.findById(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha create(@RequestBody Cozinha cozinha) {
        return service.create(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha update(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaPersistidoBd = service.findById(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaPersistidoBd, "id");

        return service.create(cozinhaPersistidoBd);
    }

    @DeleteMapping(value = "/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {
        service.delete(cozinhaId);
    }
}
