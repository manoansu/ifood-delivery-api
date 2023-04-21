package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.UsuarioNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.UsuarioRepository;

@Service
public class CadastroUsuarioService {

    private static final String MSG_USUARIO_EM_USO = "Usuário do código %d não pode ser removida, pois está em uso";
    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    public Usuario create(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_USUARIO_EM_USO, id));
        }
    }
}
