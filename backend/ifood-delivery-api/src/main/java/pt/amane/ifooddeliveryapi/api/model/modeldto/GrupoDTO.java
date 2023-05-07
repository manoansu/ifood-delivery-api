package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrupoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

//    private Set<Permissao> permissoes = new HashSet<>();
}
