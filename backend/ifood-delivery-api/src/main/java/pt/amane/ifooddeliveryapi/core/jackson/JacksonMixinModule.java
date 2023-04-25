package pt.amane.ifooddeliveryapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.mixin.CidadeMixin;
import pt.amane.ifooddeliveryapi.api.model.mixin.CozinhaMixin;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long seriaVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
