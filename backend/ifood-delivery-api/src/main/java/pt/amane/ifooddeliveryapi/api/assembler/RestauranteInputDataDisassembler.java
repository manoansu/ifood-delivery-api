package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.RestauranteInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

@Component
public class RestauranteInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo restautante recebido da propriedade RestauranteInputData.
     * @param restauranteInputData
     * @return restaurante
     */
    public Restaurante toDomainObject(RestauranteInputData restauranteInputData) {
       return modelMapper.map(restauranteInputData, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInputData restauranteInputData, Restaurante restaurante) {
        // Para evitar exception quando queremos atualizar o id da Cozinha existente na classe Restaurante para outro id.
        // vamos te que instancia Cozinha dentro do set restaurante.
        restaurante.setCozinha(new Cozinha());
        modelMapper.map(restauranteInputData, restaurante);
    }

}
