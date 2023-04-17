package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Cozinha para listar objeto cozinha
     * @return a lista de cozinha.
     */
    @Override
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto cozinha por id
     * @param id
     * @return a instancia da cozinha referenciado por id
     */
    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto cozinha
     * @param cozinha
     * @return a instancia da cozinha.
     */
    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    /**
     * Remove na tabela o bojecto cozinha
     * @param cozinha
     * @return a instancia da cozinha.
     */
    @Transactional
    @Override
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        manager.remove(cozinha);
    }
}
