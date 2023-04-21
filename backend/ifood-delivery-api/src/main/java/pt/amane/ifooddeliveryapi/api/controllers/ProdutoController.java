package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;
import pt.amane.ifooddeliveryapi.domain.repositories.ProdutoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroProdutoService;

import java.util.List;

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
    public Produto findById(@PathVariable Long produtoId) {
        return service.findById(produtoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto create(@RequestBody Produto produto) {
        return service.create(produto);
    }

    @PutMapping("/{produtoId}")
    public Produto update(@PathVariable Long produtoId, @RequestBody Cidade cidade) {
        Produto produtoPersistidoBd = service.findById(produtoId);

        BeanUtils.copyProperties(cidade, produtoPersistidoBd, "id");

        return service.create(produtoPersistidoBd);
    }
    @DeleteMapping(value = "/{produtoId}")
    public void delete(@PathVariable Long produtoId) {
        service.delete(produtoId);
    }
}
