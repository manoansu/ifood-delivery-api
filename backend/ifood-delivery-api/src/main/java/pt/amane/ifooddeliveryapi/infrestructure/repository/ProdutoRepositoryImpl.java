package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;
import pt.amane.ifooddeliveryapi.domain.repositories.ProdutoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela Produto para listar objeto produto
     * @return a lista de produto.
     */
    @Override
    public List<Produto> listar(){
        return manager.createQuery("from Produto", Produto.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto produto por id
     * @param id
     * @return a instancia da produto referenciado por id
     */
    @Override
    public Produto buscar(Long id) {
        return manager.find(Produto.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto produto
     * @param produto
     * @return a instancia da produto.
     */
    @Transactional
    @Override
    public Produto salvar(Produto produto) {
        return manager.merge(produto);
    }

    /**
     * Remove na tabela o bojecto produto
     * @param id
     * @return a instancia da produto.
     */
    @Transactional
    @Override
    public void remover(Long id) {
        Produto produto = buscar(id);
        manager.remove(produto);
    }
}
