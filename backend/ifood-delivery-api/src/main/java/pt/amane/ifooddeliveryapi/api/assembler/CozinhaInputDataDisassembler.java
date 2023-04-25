package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.CozinhaInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;

@Component
public class CozinhaInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo cozinha recebido da propriedade CozinhaInputData.
     * @param cozinhaInputData
     * @return cpzinha
     */
    public Cozinha toDomainObject(CozinhaInputData cozinhaInputData) {
       return modelMapper.map(cozinhaInputData, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInputData cozinhaInputData, Cozinha cozinha) {
        modelMapper.map(cozinhaInputData, cozinha);
    }

}
