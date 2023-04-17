package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.FormaPagamento;
import pt.amane.ifooddeliveryapi.domain.repositories.FormaPagamentoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext // injecta o EntityManager
    private EntityManager manager;

    /**
     * Executa o query da tabela formaPagamento para listar objeto formaPagamento
     * @return a lista de formaPagamento.
     */
    @Override
    public List<FormaPagamento> listar(){
        return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    /**
     * Busca na tabela o bojecto formaPagamento por id
     * @param id
     * @return a instancia da cidade referenciado por id
     */
    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    /**
     * Salva ou altera na tabela o bojecto formaPagamento
     * @param formaPagamento
     * @return a instancia da formaPagamento.
     */
    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    /**
     * Remove na tabela o bojecto formaPagamento
     * @param formaPagamento
     * @return a instancia da formaPagamento.
     */
    @Transactional
    @Override
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
