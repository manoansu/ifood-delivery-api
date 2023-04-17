package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.repositories.GrupoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class GrupoRepositoryImpl implements GrupoRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Grupo para listar objeto grupo
     * @return a lista de grupo.
     */
    @Override
    public List<Grupo> listar(){
        return manager.createQuery("from Grupo", Grupo.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto grupo por id
     * @param id
     * @return a instancia da grupo referenciado por id
     */
    @Override
    public Grupo buscar(Long id) {
        return manager.find(Grupo.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto grupo
     * @param grupo
     * @return a instancia da grupo.
     */
    @Transactional
    @Override
    public Grupo salvar(Grupo grupo) {
        return manager.merge(grupo);
    }

    /**
     * Remove na tabela o bojecto grupo
     * @param grupo
     * @return a instancia da grupo.
     */
    @Transactional
    @Override
    public void remover(Grupo grupo) {
        grupo = buscar(grupo.getId());
        manager.remove(grupo);
    }
}
