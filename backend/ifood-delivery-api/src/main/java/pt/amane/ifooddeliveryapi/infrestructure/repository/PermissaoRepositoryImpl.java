package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela permissao para listar objeto permissao
     * @return a lista de permissao.
     */
    @Override
    public List<Permissao> listar(){
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto permissao por id
     * @param id
     * @return a instancia da permissao referenciado por id
     */
    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto permissao
     * @param permissao
     * @return a instancia da permissao.
     */
    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    /**
     * Remove na tabela o bojecto permissao
     * @param id
     * @return a instancia da permissao.
     */
    @Transactional
    @Override
    public void remover(Long id) {
        Permissao permissao = buscar(id);
        manager.remove(permissao);
    }
}
