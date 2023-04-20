package pt.amane.ifooddeliveryapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    @JsonIgnore
//    @CreationTimestamp
//    @Column(nullable = false, columnDefinition = "datetime")
//    private LocalDateTime dataCadastro;
//
//    @JsonIgnore
//    @UpdateTimestamp
//    @Column(nullable = false, columnDefinition = "datetime")
//    private LocalDateTime dataAtualizacao;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCadastro;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataAtualizacao;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_restaurante_forma_pagamento",
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @JsonIgnore
    @OneToMany
    private List<Usuario> responsaveis = new ArrayList<>();

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    @PrePersist
    public void prePersist(){
        this.dataCadastro = Instant.now();
    }

    @PreUpdate
    public void PreUpdate(){
        this.dataAtualizacao = Instant.now();
    }

}
