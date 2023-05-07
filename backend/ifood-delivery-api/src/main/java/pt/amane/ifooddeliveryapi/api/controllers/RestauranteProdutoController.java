package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.ProdutoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.ProdutoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.ProdutoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.ProdutoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.repositories.ProdutoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroProdutoService;
import pt.amane.ifooddeliveryapi.domain.services.CadastroRestauranteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CadastroProdutoService service;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private ProdutoInputDataDisassembler produtoInputDataDisassembler;

    @GetMapping
    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = repository.findAll();

        return produtoDTOAssembler.toCollectionModel(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO findById(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = service.findById(restauranteId,produtoId);

        return produtoDTOAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO create(@PathVariable Long restauranteId,
                             @RequestBody @Valid ProdutoInputData produtoInputData) {
        Restaurante restaurante = cadastroRestauranteService.findById(restauranteId);

        Produto produto = produtoInputDataDisassembler.toDomainObject(produtoInputData);
        produto.setRestaurante(restaurante);

        produto = service.create(produto);

        return produtoDTOAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO upadate(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                              @RequestBody @Valid ProdutoInputData produtoInputData) {
        Produto produtoAtual = service.findById(restauranteId,produtoId);
        produtoInputDataDisassembler.copyToDomainObject(produtoInputData, produtoAtual);
        produtoAtual = service.create(produtoAtual);

        return produtoDTOAssembler.toModel(produtoAtual);
    }

//    @DeleteMapping("/{produtoId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long produtoId) {
//        service.delete(produtoId);
//    }

}
