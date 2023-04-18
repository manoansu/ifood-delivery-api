package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;
import pt.amane.ifooddeliveryapi.domain.repositories.EstadoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Estado para listar objeto estado
     * @return a lista de estado.
     */
    @Override
    public List<Estado> listar(){
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto estado por id
     * @param id
     * @return a instancia da estado referenciado por id
     */
    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto estado
     * @param estado
     * @return a instancia da estado.
     */
    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    /**
     * Remove na tabela o bojecto estado
     * @param id
     * @return a instancia da estado.
     */
    @Transactional
    @Override
    public void remover(Long id) {
        Estado estado = buscar(id);
        manager.remove(estado);
    }
}
