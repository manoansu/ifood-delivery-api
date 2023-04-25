package pt.amane.ifooddeliveryapi.api.model.modeldto.inputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FotoProdutoInputData {

    private String nomeArquivo;

    private String descrico;

    private String contentType;
    private Long tamanho;
}
