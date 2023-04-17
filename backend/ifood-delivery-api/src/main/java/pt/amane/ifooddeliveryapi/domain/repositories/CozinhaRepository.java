package pt.amane.ifooddeliveryapi.domain.repositories;

import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;

import java.util.List;

public interface CozinhaRepository {List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);


}
