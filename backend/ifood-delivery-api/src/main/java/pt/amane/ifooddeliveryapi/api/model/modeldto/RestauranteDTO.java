package pt.amane.ifooddeliveryapi.api.model.modeldto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.api.model.modeldto.view.RestauranteView;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestauranteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;
    private Boolean ativo;

    private Boolean aberto;

    private EnderecoDTO endereco;

}
