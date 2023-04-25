package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.GrupoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;

@Component
public class GrupoInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo grupo recebido da propriedade GrupoInputData.
     * @param grupoInputData
     * @return grupo
     */
    public Grupo toDomainObject(GrupoInputData grupoInputData) {
       return modelMapper.map(grupoInputData, Grupo.class);
    }

    public void copyToDomainObject(GrupoInputData grupoInputData, Grupo grupo) {
        modelMapper.map(grupoInputData, grupo);
    }

}
