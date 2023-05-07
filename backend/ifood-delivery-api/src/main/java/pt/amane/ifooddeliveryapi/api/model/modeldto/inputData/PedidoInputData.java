package pt.amane.ifooddeliveryapi.api.model.modeldto.inputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoInputData {

    @Valid
    @NotNull
    private RestauranteId restaurante;

    @Valid
    @NotNull
    private EnderecoInputData enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoId formaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInputData> itens;
}
