package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.FormaPagamentoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.FormaPagamentoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.services.CadastroRestauranteService;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController {

    @Autowired
    private CadastroRestauranteService service;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;


    @GetMapping
    public List<FormaPagamentoDTO> findAll(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);

        return formaPagamentoDTOAssembler.toCollectionModel(restaurante.getFormasPagamento());
    }

    @DeleteMapping(value = "/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        service.desassociarFormaPagamento(restauranteId, formaPagamentoId);

    }

    @PutMapping(value = "/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        service.associarFormaPagamento(restauranteId, formaPagamentoId);

    }


}
