package pt.amane.ifooddeliveryapi.api.model.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersioUID = 1L;

    private Long id;

    private String nome;
    private String email;
    private String senha;

    private Instant dataCadastro;

    private Instant dataAtualizacao;

    private Set<Grupo> grupos = new HashSet<>();

}
