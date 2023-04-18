package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pt.amane.ifooddeliveryapi.domain.entities.Produto;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.ProdutoRepository;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.salvar(produto);
    }

    public void remover(Long id) {
        try {
            repository.remover(id);

        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
            String.format("Não existe cadstro de produto  com código %d ", id));

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format("Produto do código %d não pode ser removida, pois está em uso", id));
        }
    }

}
