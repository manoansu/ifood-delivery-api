package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.PermissaoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;

@Component
public class PermissaoInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo permissao recebido da propriedade PermissaoInputData.
     * @param permissaoInputData
     * @return permissao
     */
    public Permissao toDomainObject(PermissaoInputData permissaoInputData) {
       return modelMapper.map(permissaoInputData, Permissao.class);
    }

    public void copyToDomainObject(PermissaoInputData permissaoInputData, Permissao permissao) {
        modelMapper.map(permissaoInputData, permissao);
    }

}
