package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPermissaoService;

import java.util.List;

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
    public Permissao findById(@PathVariable Long permissaoId) {
        return service.findById(permissaoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permissao create(@RequestBody Permissao permissao) {
        return service.create(permissao);
    }

    @PutMapping("/{permissaoId}")
    public Permissao update(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
        Permissao permissaoPersistidoBd = service.findById(permissaoId);

        BeanUtils.copyProperties(permissao, permissaoPersistidoBd, "id");

        return service.create(permissaoPersistidoBd);
    }
    @DeleteMapping(value = "/{permissaoId}")
    public void delete(@PathVariable Long permissaoId) {
        service.delete(permissaoId);
    }
}
