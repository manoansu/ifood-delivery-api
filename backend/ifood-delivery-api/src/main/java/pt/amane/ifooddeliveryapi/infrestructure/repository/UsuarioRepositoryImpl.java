package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.repositories.UsuarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Usuario para listar objeto usuario
     * @return a lista de usuario.
     */
    @Override
    public List<Usuario> listar(){
        return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto usuario por id
     * @param id
     * @return a instancia da usuario referenciado por id
     */
    @Override
    public Usuario buscar(Long id) {
        return manager.find(Usuario.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto cozinha
     * @param usuario
     * @return a instancia da cozinha.
     */
    @Transactional
    @Override
    public Usuario salvar(Usuario usuario) {
        return manager.merge(usuario);
    }

    /**
     * Remove na tabela o bojecto usuario
     * @param id
     * @return a instancia da usuario.
     */
    @Transactional
    @Override
    public void remover(Long id) {
        Usuario usuario = buscar(id);
        manager.remove(usuario);
    }
}
