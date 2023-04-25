package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.ProdutoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de ProdutoDTO para Produto..
     * @param produto
     * @return produtoDTO
     */
    public ProdutoDTO toModel(Produto produto) {
       return modelMapper.map(produto, ProdutoDTO.class);
    }

    /**
     * Transforma o lista de Produto para produtoDTO.
     * @param produtos
     * @return produtoDTOs
     */
    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
