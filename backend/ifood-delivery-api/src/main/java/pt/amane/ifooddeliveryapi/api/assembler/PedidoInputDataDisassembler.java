package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.PedidoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;

@Component
public class PedidoInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo pedido recebido da propriedade PedidoInputData.
     * @param pedidoInputData
     * @return pedido
     */
    public Pedido toDomainObject(PedidoInputData pedidoInputData) {
       return modelMapper.map(pedidoInputData, Pedido.class);
    }

    public void copyToDomainObject(PedidoInputData pedidoInputData, Pedido pedido) {
        modelMapper.map(pedidoInputData, pedido);
    }

}
