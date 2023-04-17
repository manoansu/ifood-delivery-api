package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Restaurante para listar objeto restaurante
     * @return a lista de restaurante.
     */
    @Override
    public List<Restaurante> listar(){
        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto Restaurante por id
     * @param id
     * @return a instancia da restaurante referenciado por id
     */
    @Override
    public Restaurante buscar(Long id) {
        return manager.find(Restaurante.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto restaurante
     * @param restaurante
     * @return a instancia da restaurante.
     */
    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    /**
     * Remove na tabela o bojecto restaurante
     * @param restaurante
     * @return a instancia da restaurante.
     */
    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
