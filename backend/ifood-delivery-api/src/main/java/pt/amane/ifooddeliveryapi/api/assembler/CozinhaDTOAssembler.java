package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.CozinhaDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de CozinhaDTO para Cozinha..
     * @param cozinha
     * @return cozinhaDTO
     */
    public CozinhaDTO toModel(Cozinha cozinha) {
       return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    /**
     * Transforma o lista de Cozinha para CozinhaDTO.
     * @param cozinhas
     * @return cozinhaDTOs
     */
    public List<CozinhaDTO> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    }
}
