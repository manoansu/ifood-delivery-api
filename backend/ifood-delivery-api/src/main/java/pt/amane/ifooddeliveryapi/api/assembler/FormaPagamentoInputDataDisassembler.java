package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.FormaPagamentoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;

@Component
public class FormaPagamentoInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo formaPagamento recebido da propriedade FormaPagamentoInputData.
     * @param formaPagamentoInputData
     * @return formaPagamento
     */
    public FormaPagamento toDomainObject(FormaPagamentoInputData formaPagamentoInputData) {
       return modelMapper.map(formaPagamentoInputData, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInputData formaPagamentoInputData, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInputData, formaPagamento);
    }

}
