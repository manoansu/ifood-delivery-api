package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPermissaoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController {

    @Autowired
    private PermissaoRepository repository;

    @Autowired
    private CadastroPermissaoService service;

    @GetMapping
    public List<Permissao> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{permissaoId}")
    public ResponseEntity<Permissao> findById(@PathVariable Long permissaoId) {

        Optional<Permissao> permissaoOptional = repository.findById(permissaoId);

        if (permissaoOptional.isPresent()) {
            return ResponseEntity.ok(permissaoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permissao create(@RequestBody Permissao permissao) {
        return service.create(permissao);
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<Permissao> update(@PathVariable Long permissaoId,
                                         @RequestBody Permissao permissao) {
        Optional<Permissao> permissaoPersistidoBd = repository.findById(permissaoId);

        if (permissaoPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(permissao, permissaoPersistidoBd.get(), "id");

            Permissao permissaoSalva = repository.save(permissaoPersistidoBd.get());
            return ResponseEntity.ok(permissaoSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{permissaoId}")
    public ResponseEntity<Permissao> delete(@PathVariable Long permissaoId) {

        try {
            service.delete(permissaoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
