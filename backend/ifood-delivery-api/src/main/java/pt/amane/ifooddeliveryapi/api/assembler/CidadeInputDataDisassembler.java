package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.CidadeInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;

@Component
public class CidadeInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo Cidade recebido da propriedade CidadeInputData.
     * @param cidadeInputData
     * @return cidade
     */
    public Cidade toDomainObject(CidadeInputData cidadeInputData) {
       return modelMapper.map(cidadeInputData, Cidade.class);
    }

    public void copyToDomainObject(CidadeInputData cidadeInputData, Cidade cidade) {
        // Para evitar exception quando queremos atualizar o id da estado existente na classe Cidade para outro id.
        // vamos te que instancia estado dentro do set estado.
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInputData, cidade);
    }

}
