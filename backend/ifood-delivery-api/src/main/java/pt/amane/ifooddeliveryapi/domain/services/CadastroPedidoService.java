package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.PedidoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;

@Service
public class CadastroPedidoService {

    private static final String MSG_PEDIDO_EM_USO = "Pedido do código %d não pode ser removida, pois está em uso";

    @Autowired
    private PedidoRepository repository;

    @Transactional(readOnly = true)
    public Pedido findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Pedido create(Pedido pedido) {
        return repository.save(pedido);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new PedidoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_PEDIDO_EM_USO, id));
        }
    }
}
