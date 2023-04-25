package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.domain.entities.ItemPedido;
import pt.amane.ifooddeliveryapi.domain.entities.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private Instant dataCriacao;
    private Instant dataConfirmacao;
    private Instant dataCancelamento;
    private Instant dataEntrega;

    private Endereco enderecoEntrega;

    private StatusPedido statusPedido;

    private FormaPagamento formaPagamento;

    private Usuario cliente;

    private Restaurante restaurante;

    private List<ItemPedido> itemPedidos = new ArrayList<>();
}
