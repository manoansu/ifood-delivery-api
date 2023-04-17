package pt.amane.ifooddeliveryapi.domain.entities;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Cozinha
     * @return a lista de cozinha.
     */
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto cozinha por id
     * @param id
     * @return a instancia da cozinha referenciado por id
     */
    public Cozinha busca(Long id) {
        return manager.find(Cozinha.class, id);
    }

    /**
     * Adiciona na tabela o bojecto cozinha
     * @param cozinha
     * @return a instancia da cozinha.
     */
    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

}
