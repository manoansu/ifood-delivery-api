package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoResumoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoResumoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.PedidoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;
import pt.amane.ifooddeliveryapi.domain.services.EmissaoPedidoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private EmissaoPedidoService service;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoInputDataDisassembler pedidoInputDataDisassembler;

    @GetMapping
    public List<PedidoResumoDTO> findAll() {
        List<Pedido> pedidos = repository.findAll();

        return pedidoResumoDTOAssembler.toCollectionModel(pedidos);
    }

    @GetMapping("/{codigoPedidoId}")
    public PedidoDTO findById(@PathVariable String  codigoPedidoId) {
        Pedido pedido = service.findById(codigoPedidoId);

        return pedidoDTOAssembler.toModel(pedido);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO create(@Valid @RequestBody PedidoInputData pedidoInputData) {
        try {
            Pedido novoPedido = pedidoInputDataDisassembler.toDomainObject(pedidoInputData);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoDTOAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

//    @PutMapping("/{pedidoId}")
//    public PedidoDTO upadate(@PathVariable Long pedidoId,
//                              @RequestBody @Valid PedidoInputData pedidoInputData) {
//        Pedido pedidotual = service.findById(pedidoId);
//        pedidoInputDataDisassembler.copyToDomainObject(pedidoInputData, pedidotual);
//        pedidotual = service.create(pedidotual);
//
//        return pedidoDTOAssembler.toModel(pedidotual);
//    }

//    @DeleteMapping("/{pedidoId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long pedidoId) {
//        service.delete(pedidoId);
//    }

}
