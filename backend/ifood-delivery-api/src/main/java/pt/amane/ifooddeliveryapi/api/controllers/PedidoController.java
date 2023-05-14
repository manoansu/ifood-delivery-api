package pt.amane.ifooddeliveryapi.api.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
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
import pt.amane.ifooddeliveryapi.domain.repositories.filters.PedidoFilter;
import pt.amane.ifooddeliveryapi.domain.services.EmissaoPedidoService;
import pt.amane.ifooddeliveryapi.infrastructure.repository.spec.PedidoSpecs;

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

//    @GetMapping
//    public MappingJacksonValue findAll(@RequestParam(required = false) String campos) {
//            // Busca a lista de pedidos na BD.
//            List<Pedido> restaurantes = repository.findAll();
//
//            //Converte  o tipo pedidos para DTO.
//            List<PedidoDTO> pedidoDTOS = pedidoDTOAssembler.toCollectionModel(restaurantes);
//
//            //Coloca  o pedidos DTO dentro de um wrappe ou envelopa-o
//            MappingJacksonValue pedidoWrapper = new MappingJacksonValue(pedidoDTOS);
//
//            SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//            // Permite filtrar a propriedades que qeuer exibir, pode ser um elemento ou mais a unica diferença é a virgual que conta qual campo que vai ser exibido..
//            if (StringUtils.isNoneBlank(campos)) {
//                filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//            }
//
//            pedidoWrapper.setFilters(filterProvider);
//
//            return pedidoWrapper;
//    }


    @GetMapping
    public Page<PedidoResumoDTO> search(PedidoFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        Page<Pedido> pedidos = repository.findAll(PedidoSpecs.usandoFiltro(filter), pageable);

        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoResumoDTOAssembler.toCollectionModel(pedidos.getContent());

        Page<PedidoResumoDTO> pedidoResumoDTOPage = new PageImpl<>(pedidoResumoDTOS, pageable ,pedidos.getTotalPages());

        return pedidoResumoDTOPage;
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
