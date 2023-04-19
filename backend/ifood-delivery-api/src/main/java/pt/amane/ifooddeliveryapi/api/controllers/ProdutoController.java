package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.ProdutoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CadastroProdutoService service;

    @GetMapping
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{produtoId}")
    public ResponseEntity<Produto> findById(@PathVariable Long produtoId) {

        Optional<Produto> produtoOptional = repository.findById(produtoId);

        if (produtoOptional.isPresent()) {
            return ResponseEntity.ok(produtoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto create(@RequestBody Produto produto) {
        return service.create(produto);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> update(@PathVariable Long produtoId,
                                         @RequestBody Cidade cidade) {
        Optional<Produto> produtoPersistidoBd = repository.findById(produtoId);

        if (produtoPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(cidade, produtoPersistidoBd.get(), "id");

            Produto produtoSalva = repository.save(produtoPersistidoBd.get());
            return ResponseEntity.ok(produtoSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{produtoId}")
    public ResponseEntity<Produto> delete(@PathVariable Long produtoId) {

        try {
            service.delete(produtoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
