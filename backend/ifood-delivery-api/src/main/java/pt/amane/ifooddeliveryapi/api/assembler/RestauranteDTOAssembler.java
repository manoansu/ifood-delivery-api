package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.CozinhaDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.RestauranteDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de RestauranteDTO para Restaurante..
     * @param restaurante
     * @return restauranteDTO
     */
    public RestauranteDTO toModel(Restaurante restaurante) {
       return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    /**
     * Transforma o lista de Restaurante para restauranteDTO.
     * @param restaurantes
     * @return restauranteDTOs
     */
    public List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
