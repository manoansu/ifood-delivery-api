package pt.amane.ifooddeliveryapi.api.model.modeldto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.api.model.modeldto.view.RestauranteView;
import pt.amane.ifooddeliveryapi.domain.entities.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Instant dataCadastro;

    private Instant dataAtualizacao;

    private EnderecoDTO endereco;

    private List<Produto> produtos = new ArrayList<>();

    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    private List<Usuario> responsaveis = new ArrayList<>();
}
