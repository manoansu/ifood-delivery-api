package pt.amane.ifooddeliveryapi.api.model.modeldto.inputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioInputData {

    @NotBlank
    private String nome;
    private String email;
    private String senha;

    private Instant dataCadastro;

    private Instant dataAtualizacao;

    @Valid
    @NotNull
    private RestauranteId restaurante;
}
