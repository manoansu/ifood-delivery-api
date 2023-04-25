package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.CidadeDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de CidadeDTO para cidade..
     * @param cidade
     * @return cidadeDTO
     */
    public CidadeDTO toModel(Cidade cidade) {
       return modelMapper.map(cidade, CidadeDTO.class);
    }

    /**
     * Transforma o lista de Cidade para cidadeDTO.
     * @param cidades
     * @return cidadeDTOs
     */
    public List<CidadeDTO> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
