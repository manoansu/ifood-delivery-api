package pt.amane.ifooddeliveryapi.api.model.modeldto.inputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoInputData {

    @NotBlank
    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean ativo;
}
