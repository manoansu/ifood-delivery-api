package pt.amane.ifooddeliveryapi.domain.repositories;

import pt.amane.ifooddeliveryapi.domain.entities.Produto;

import java.util.List;

public interface ProdutoRepository {

    List<Produto> listar();
    Produto buscar(Long id);
    Produto salvar(Produto produto);
    void remover(Long id);
}
