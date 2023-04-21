package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.ProdutoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.ProdutoRepository;

@Service
public class CadastroProdutoService {

    private static final String MSG_PRODUTO_EM_USO = "Produto do código %d não pode ser removida, pois está em uso";
    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public Produto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_PRODUTO_EM_USO, id));
        }
    }
}
