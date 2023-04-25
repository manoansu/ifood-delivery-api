package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CozinhaDTO implements Serializable {

    private static final long serialVersioUID = 1L;

    private Long id;

    private String nome;

    private List<Restaurante> restaurantes = new ArrayList<>();
}
