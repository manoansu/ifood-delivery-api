package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Pedido para listar objeto pedido
     * @return a lista de pedido.
     */
    @Override
    public List<Pedido> listar(){
        return manager.createQuery("from Pedido", Pedido.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto pedido por id
     * @param id
     * @return a instancia da pedido referenciado por id
     */
    @Override
    public Pedido buscar(Long id) {
        return manager.find(Pedido.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto pedido
     * @param pedido
     * @return a instancia da pedido.
     */
    @Transactional
    @Override
    public Pedido salvar(Pedido pedido) {
        return manager.merge(pedido);
    }

    /**
     * Remove na tabela o bojecto pedido
     * @param pedido
     * @return a instancia da pedido.
     */
    @Transactional
    @Override
    public void remover(Pedido pedido) {
        pedido = buscar(pedido.getId());
        manager.remove(pedido);
    }
}
