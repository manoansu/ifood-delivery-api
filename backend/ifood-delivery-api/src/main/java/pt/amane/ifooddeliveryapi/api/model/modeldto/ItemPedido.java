package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;

    private Produto produto;

    private Pedido pedido;

}
