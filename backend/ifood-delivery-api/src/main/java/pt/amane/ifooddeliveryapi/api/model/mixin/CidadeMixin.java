package pt.amane.ifooddeliveryapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
