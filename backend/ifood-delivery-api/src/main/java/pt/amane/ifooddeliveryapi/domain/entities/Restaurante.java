package pt.amane.ifooddeliveryapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_restaurante")
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;
    private Boolean ativo;
    private Boolean activo;
    private Instant dataCadastro;
    private Instant dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @OneToMany
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany
    private List<Usuario> responsaveis = new ArrayList<>();

}
