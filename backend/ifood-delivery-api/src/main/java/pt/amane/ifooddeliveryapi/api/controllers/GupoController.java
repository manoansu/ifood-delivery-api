package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.GrupoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroGruoService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Grupo> findById(@PathVariable Long grupoId) {

        Optional<Grupo> grupoOptional = repository.findById(grupoId);

        if (grupoOptional.isPresent()) {
            return ResponseEntity.ok(grupoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grupo create(@RequestBody Grupo grupo) {
        return service.create(grupo);
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<Grupo> update(@PathVariable Long grupoId,
                                         @RequestBody Cidade cidade) {
        Optional<Grupo> grupoPersistidoBd = repository.findById(grupoId);

        if (grupoPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(cidade, grupoPersistidoBd.get(), "id");

            Grupo grupoSalva = repository.save(grupoPersistidoBd.get());
            return ResponseEntity.ok(grupoSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{grupoId}")
    public ResponseEntity<Grupo> delete(@PathVariable Long grupoId) {

        try {
            service.delete(grupoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
