package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.PedidoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPedidoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private CadastroPedidoService service;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @Autowired
    private PedidoInputDataDisassembler pedidoInputDataDisassembler;

    @GetMapping
    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = repository.findAll();

        return pedidoDTOAssembler.toCollectionModel(pedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO findById(@PathVariable Long pedidoId) {
        Pedido pedido = service.findById(pedidoId);

        return pedidoDTOAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO create(@RequestBody @Valid PedidoInputData pedidoInputData) {
        Pedido pedido = pedidoInputDataDisassembler.toDomainObject(pedidoInputData);
        pedido = service.create(pedido);

        return pedidoDTOAssembler.toModel(pedido);
    }

    @PutMapping("/{pedidoId}")
    public PedidoDTO upadate(@PathVariable Long pedidoId,
                              @RequestBody @Valid PedidoInputData pedidoInputData) {
        Pedido pedidotual = service.findById(pedidoId);
        pedidoInputDataDisassembler.copyToDomainObject(pedidoInputData, pedidotual);
        pedidotual = service.create(pedidotual);

        return pedidoDTOAssembler.toModel(pedidotual);
    }

    @DeleteMapping("/{pedidoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long pedidoId) {
        service.delete(pedidoId);
    }

}
