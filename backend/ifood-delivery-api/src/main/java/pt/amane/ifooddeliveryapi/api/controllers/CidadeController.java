package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.CidadeRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroCidadeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CidadeRepository repository;
    
    @Autowired
    private CadastroCidadeService service;

    @GetMapping
    public List<Cidade> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cidade> findById(@PathVariable Long cozinhaId) {
        
        Optional<Cidade> cidadeOptional = repository.findById(cozinhaId);
        
        if (cidadeOptional.isPresent()) {
            return ResponseEntity.ok(cidadeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade create(@RequestBody Cidade cidade) {
        return service.salvar(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> update(@PathVariable Long cidadeId,
                                          @RequestBody Cidade cidade) {
        Optional<Cidade> cidadePersistidoBd = repository.findById(cidadeId);

        if (cidadePersistidoBd.isPresent()) {
            BeanUtils.copyProperties(cidade, cidadePersistidoBd.get(), "id");

            Cidade cidadeSalva = repository.save(cidadePersistidoBd.get());
            return ResponseEntity.ok(cidadeSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{cidadeId}")
    public ResponseEntity<Cidade> delete(@PathVariable Long cidadeId) {

        try {
            service.remover(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
