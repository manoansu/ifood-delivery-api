package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoResumoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de PedidoDTO para Pedido..
     * @param pedido
     * @return pedidoDTO
     */
    public PedidoResumoDTO toModel(Pedido pedido) {
       return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    /**
     * Transforma o lista de Pedido para pedidoDTO.
     * @param pedidos
     * @return pedidoDTOs
     */
    public List<PedidoResumoDTO> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
