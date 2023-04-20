package pt.amane.ifooddeliveryapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersioUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String email;
    private String senha;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCadastro;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "tb_usuario_gruppo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private Set<Grupo> grupos = new HashSet<>();

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
