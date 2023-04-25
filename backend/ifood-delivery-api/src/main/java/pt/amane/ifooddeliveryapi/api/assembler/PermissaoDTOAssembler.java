package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PermissaoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de PermissaoDTO para Permissao..
     * @param permissao
     * @return permissaoDTO
     */
    public PermissaoDTO toModel(Permissao permissao) {
       return modelMapper.map(permissao, PermissaoDTO.class);
    }

    /**
     * Transforma o lista de Permissao para permissaoDTO.
     * @param permissoes
     * @return permissaoDTOs
     */
    public List<PermissaoDTO> toCollectionModel(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
