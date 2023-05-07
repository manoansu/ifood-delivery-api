package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.FormaPagamentoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de FormaPagamentoDTO para FormaPagamento..
     * @param formaPagamento
     * @return formaPagamentoDTO
     */
    public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
       return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    /**
     * Transforma o lista de FormaPagamento para formaPagamentoDTO.
     * @param formaPagamentos
     * @return formaPagamentoDTOs
     */
    public List<FormaPagamentoDTO> toCollectionModel(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
