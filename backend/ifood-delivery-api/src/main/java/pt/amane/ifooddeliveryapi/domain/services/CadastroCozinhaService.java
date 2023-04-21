package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.exception.CozinhaNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;

@Service
public class CadastroCozinhaService {

    private static final String MSG_COZINHA_EM_USO = "Cozinha do código %d não pode ser removida, pois está em uso";
    @Autowired
    private CozinhaRepository repository;

    @Transactional(readOnly = true)
    public Cozinha findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradoException(id));
    }

    public Cozinha create(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_COZINHA_EM_USO, id));
        }
    }
}
