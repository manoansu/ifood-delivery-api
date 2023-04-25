package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.ProdutoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;

@Component
public class ProdutoInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo produto recebido da propriedade ProdutoInputData.
     * @param produtoInputData
     * @return produto
     */
    public Produto toDomainObject(ProdutoInputData produtoInputData) {
       return modelMapper.map(produtoInputData, Produto.class);
    }

    public void copyToDomainObject(ProdutoInputData produtoInputData, Produto produto) {
        modelMapper.map(produtoInputData, produto);
    }

}
