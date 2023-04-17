package pt.amane.ifooddeliveryapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_fotoproduto")
public class FotoProduto implements Serializable {

    private static final long serialVersioUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeArquivo;
    @Column(nullable = false)
    private String descrico;

    @Column(nullable = false)
    private String contentType;
    private Long tamanho;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
}
