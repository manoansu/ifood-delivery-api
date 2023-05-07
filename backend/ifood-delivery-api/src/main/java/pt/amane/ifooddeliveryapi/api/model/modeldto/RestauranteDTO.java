package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private Long id;

    private String nome;

    private BigDecimal taxaFrete;
    private Boolean ativo;

    private Boolean aberto;

    private Instant dataCadastro;

    private Instant dataAtualizacao;

    private CozinhaDTO cozinha;
    private EnderecoDTO endereco;

    private List<Produto> produtos = new ArrayList<>();

    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    private List<Usuario> responsaveis = new ArrayList<>();
}
