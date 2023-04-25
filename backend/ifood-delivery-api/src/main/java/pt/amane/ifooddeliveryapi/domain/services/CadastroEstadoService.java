package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EstadoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.EstadoRepository;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO = "Estado do código %d não pode ser removida, pois está em uso";

    @Autowired
    private EstadoRepository repository;

    @Transactional(readOnly = true)
    public Estado findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Estado create(Estado estado) {
        return repository.save(estado);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_ESTADO_EM_USO, id));
        }
    }
}
