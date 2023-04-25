package pt.amane.ifooddeliveryapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;

}
