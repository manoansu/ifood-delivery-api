package pt.amane.ifooddeliveryapi.api.model.modeldto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.api.model.modeldto.view.RestauranteView;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CozinhaDTO implements Serializable {

    private static final long serialVersioUID = 1L;

    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
