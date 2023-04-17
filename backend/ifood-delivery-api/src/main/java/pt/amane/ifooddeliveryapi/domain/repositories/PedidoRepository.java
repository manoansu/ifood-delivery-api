package pt.amane.ifooddeliveryapi.domain.repositories;

import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;

import java.util.List;

public interface PedidoRepository {

    List<Pedido> listar();
    Pedido buscar(Long id);
    Pedido salvar(Pedido pedido);
    void remover(Pedido pedido);


}
