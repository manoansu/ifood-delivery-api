package pt.amane.ifooddeliveryapi.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @JsonIgnore
    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @CreationTimestamp
    private Instant dataCriacao;

    private Instant dataConfirmacao;
    private Instant dataCancelamento;
    private Instant dataEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItens().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmar() {
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(Instant.now());
    }

    public void entregar() {
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(Instant.now());
    }

    public void cancelar() {
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(Instant.now());
    }

    private void setStatus(StatusPedido novoStatus) {
        if (getStatus().naoPodeAlterarStatus(novoStatus)) {
            throw new NegocioException(
                    String.format("Status do pedido %s não pode ser alterado de %s para %s",
                            getCodigo(), getStatus().getDescricao(),
                            novoStatus.getDescricao()));
        }

        this.status = novoStatus;
    }

    /**
     * PrePersist Metodo de coll back de java que gera o codigo para um determinado evento
     * antes de chamar inserir elemento ou executar qualquer instruções.
     */
    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

}
