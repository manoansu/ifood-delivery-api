package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.exception.EstadoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;
import pt.amane.ifooddeliveryapi.domain.repositories.CidadeRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroCidadeService;

import java.util.List;

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
    public Cidade findById(@PathVariable Long cozinhaId) {
        return service.findById(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade create(@RequestBody Cidade cidade) {

        try {
            return service.create(cidade);
        }catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                            @RequestBody Cidade cidade) {
        Cidade cidadeAtual = service.findById(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return service.create(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    @DeleteMapping(value = "/{cidadeId}")
    public void delete(@PathVariable Long cidadeId) {
        service.delete(cidadeId);

    }
}
