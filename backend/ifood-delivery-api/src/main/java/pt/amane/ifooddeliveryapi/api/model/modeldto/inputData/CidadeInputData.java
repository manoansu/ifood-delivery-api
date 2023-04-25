package pt.amane.ifooddeliveryapi.api.model.modeldto.inputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CidadeInputData {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoId estado;
}
