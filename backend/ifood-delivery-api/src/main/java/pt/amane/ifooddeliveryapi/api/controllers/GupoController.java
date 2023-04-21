package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.repositories.GrupoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroGruoService;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GupoController {

    @Autowired
    private GrupoRepository repository;

    @Autowired
    private CadastroGruoService service;

    @GetMapping
    public List<Grupo> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{grupoId}")
    public Grupo findById(@PathVariable Long grupoId) {
        return service.findById(grupoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grupo create(@RequestBody Grupo grupo) {
        return service.create(grupo);
    }

    @PutMapping("/{grupoId}")
    public Grupo update(@PathVariable Long grupoId, @RequestBody Cidade cidade) {
        Grupo grupoPersistidoBd = service.findById(grupoId);

        BeanUtils.copyProperties(cidade, grupoPersistidoBd, "id");

        return service.create(grupoPersistidoBd);
    }
    @DeleteMapping(value = "/{grupoId}")
    public void delete(@PathVariable Long grupoId) {
        service.delete(grupoId);
    }
}
