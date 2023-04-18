package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.salvar(formaPagamento);
    }

    public void remover(Long id) {
        try {
            repository.remover(id);

        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
            String.format("Não existe cadstro de forma de pagamento  com código %d ", id));

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format("Forma de pagamento do código %d não pode ser removida, pois está em uso", id));
        }
    }

}
