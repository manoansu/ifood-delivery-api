package pt.amane.ifooddeliveryapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.amane.ifooddeliveryapi.api.model.modeldto.RestauranteDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelmaper = new ModelMapper();

        modelmaper.createTypeMap(Restaurante.class, RestauranteDTO.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setTaxaFrete);
        return modelmaper;
    }
}
