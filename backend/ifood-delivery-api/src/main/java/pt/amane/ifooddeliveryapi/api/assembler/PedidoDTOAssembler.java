package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PedidoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de PedidoDTO para Pedido..
     * @param pedido
     * @return pedidoDTO
     */
    public PedidoDTO toModel(Pedido pedido) {
       return modelMapper.map(pedido, PedidoDTO.class);
    }

    /**
     * Transforma o lista de Pedido para pedidoDTO.
     * @param pedidos
     * @return pedidoDTOs
     */
    public List<PedidoDTO> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
