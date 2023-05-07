package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.GrupoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de GrupoDTO para Grupo..
     * @param grupo
     * @return grupoDTO
     */
    public GrupoDTO toModel(Grupo grupo) {
       return modelMapper.map(grupo, GrupoDTO.class);
    }

    /**
     * Transforma o lista de Grupo para grupoDTO.
     * @param grupos
     * @return grupoDTOs
     */
    public List<GrupoDTO> toCollectionModel(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
