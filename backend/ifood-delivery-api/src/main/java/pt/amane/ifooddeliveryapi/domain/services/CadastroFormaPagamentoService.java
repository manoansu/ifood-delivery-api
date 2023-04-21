package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.FormaPagamentoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento do código %d não pode ser removida, pois está em uso";

    @Autowired
    private FormaPagamentoRepository repository;

    @Transactional(readOnly = true)
    public FormaPagamento findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradoException(id));
    }

    public FormaPagamento create(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        }
    }
}
