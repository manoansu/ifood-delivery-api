package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.EstadoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;

@Component
public class EstadoInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo estado recebido da propriedade EstadoInputData.
     * @param estadoInputData
     * @return estado
     */
    public Estado toDomainObject(EstadoInputData estadoInputData) {
       return modelMapper.map(estadoInputData, Estado.class);
    }

    public void copyToDomainObject(EstadoInputData estadoInputData, Estado estado) {
        modelMapper.map(estadoInputData, estado);
    }

}
