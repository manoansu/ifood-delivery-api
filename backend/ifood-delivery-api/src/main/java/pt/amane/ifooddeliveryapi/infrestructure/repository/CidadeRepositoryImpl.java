package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.repositories.CidadeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela cidade para listar objeto cidade
     * @return a lista de cidade.
     */
    @Override
    public List<Cidade> listar(){
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto cidade por id
     * @param id
     * @return a instancia da cidade referenciado por id
     */
    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto cidade
     * @param cidade
     * @return a instancia da cidade.
     */
    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    /**
     * Remove na tabela o bojecto cidade
     * @param cidade
     * @return a instancia da cidade.
     */
    @Transactional
    @Override
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
