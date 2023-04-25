package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.EstadoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de EstadoDTO para Estado..
     * @param estado
     * @return estadoDTO
     */
    public EstadoDTO toModel(Estado estado) {
       return modelMapper.map(estado, EstadoDTO.class);
    }

    /**
     * Transforma o lista de Estado para estadoDTO.
     * @param estados
     * @return estadoDTOs
     */
    public List<EstadoDTO> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
