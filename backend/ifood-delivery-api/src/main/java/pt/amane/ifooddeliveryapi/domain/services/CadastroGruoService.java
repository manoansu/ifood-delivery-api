package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.GrupoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.GrupoRepository;

@Service
public class CadastroGruoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo do código %d não pode ser removida, pois está em uso";

    @Autowired
    private GrupoRepository repository;

    @Transactional(readOnly = true)
    public Grupo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Grupo create(Grupo grupo) {
        return repository.save(grupo);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_GRUPO_EM_USO, id));
        }
    }
}
