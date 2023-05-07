package pt.amane.ifooddeliveryapi.api.model.modeldto.inputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioInputData {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
//    private String senha;
//
//    private Instant dataCadastro;
//
//    private Instant dataAtualizacao;
//
//    @Valid
//    @NotNull
//    private RestauranteId restaurante;
}
