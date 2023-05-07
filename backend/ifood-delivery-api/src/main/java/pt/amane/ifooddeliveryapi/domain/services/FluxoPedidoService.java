package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(String  codigoPedidoId) {
        Pedido pedido = emissaoPedidoService.findById(codigoPedidoId);
        pedido.confirmar();
    }
    @Transactional
    public void cancelar(String codigoPedidoId) {
        Pedido pedido = emissaoPedidoService.findById(codigoPedidoId);
        pedido.cancelar();
    }
    @Transactional
    public void entregar(String codigoPedidoId) {
        Pedido pedido = emissaoPedidoService.findById(codigoPedidoId);
        pedido.entregar();
    }
}
