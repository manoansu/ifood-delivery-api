package pt.amane.ifooddeliveryapi.api.controllers;

import com.sun.net.httpserver.Headers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.model.CozinhasXmlWrapper;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @GetMapping
    public List<Cozinha> listar() {
        return repository.listar();
    }

    @GetMapping( produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(repository.listar());
    }

    @GetMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = repository.buscar(cozinhaId);

//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//        return ResponseEntity.ok().body(cozinha);
//        return ResponseEntity.ok(cozinha);

//        Headers headers = new Headers();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity.status(HttpStatus.FOUND).headers((Consumer<HttpHeaders>) headers).build();

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

    @PutMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,@RequestBody Cozinha cozinha) {
        Cozinha cozinhaPersistidoBd = repository.buscar(cozinhaId);

        if (cozinhaPersistidoBd != null){

    //        cozinhaPersistidoBd.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaPersistidoBd, "id");
            repository.salvar(cozinha);

            return ResponseEntity.ok(cozinhaPersistidoBd);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {

        try {
            Cozinha cozinha = repository.buscar(cozinhaId);

            if (cozinha != null) {
                repository.remover(cozinha);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
