package pt.amane.ifooddeliveryapi.api.controllers;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.assembler.PedidoResumoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoResumoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.PedidoInputData;
import pt.amane.ifooddeliveryapi.core.data.PageableTranslator;
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
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

    @Autowired
    private PedidoInputDataDisassembler pedidoInputDataDisassembler;

    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(PedidoFilter filtro,
                                           @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoDTO> pedidosResumoModel = pedidoResumoDTOAssembler
                .toCollectionModel(pedidosPage.getContent());

        Page<PedidoResumoDTO> pedidosResumoModelPage = new PageImpl<>(
                pedidosResumoModel, pageable, pedidosPage.getTotalElements());

        return pedidosResumoModelPage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInputData pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDataDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoDTOAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.findById(codigoPedido);

        return pedidoDTOAssembler.toModel(pedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }

}