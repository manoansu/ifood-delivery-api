package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.PermissaoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;

@Service
public class CadastroPermissaoService {

    private static final String MSG_PERMISSAO_EM_USO = "Permissao do código %d não pode ser removida, pois está em uso";
    @Autowired
    private PermissaoRepository repository;

    @Transactional(readOnly = true)
    public Permissao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PermissaoNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Permissao create(Permissao permissao) {
        return repository.save(permissao);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new PermissaoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_PERMISSAO_EM_USO, id));
        }
    }
}
