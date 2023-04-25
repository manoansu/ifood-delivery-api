package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FotoProdutoDTO implements Serializable {

    private static final long serialVersioUID = 1L;

    private Long id;

    private String nomeArquivo;

    private String descrico;

    private String contentType;
    private Long tamanho;

    private Produto produto;
}
