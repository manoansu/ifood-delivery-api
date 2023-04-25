package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormaPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String descricao;
}
