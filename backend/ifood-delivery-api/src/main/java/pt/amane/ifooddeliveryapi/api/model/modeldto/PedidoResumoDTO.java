package pt.amane.ifooddeliveryapi.api.model.modeldto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@JsonFilter("pedidoFilter")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private Instant dataCriacao;
    private RestauranteResumoDTO restaurante;
    private UsuarioDTO cliente;
}
