package pt.amane.ifooddeliveryapi.domain.repositories;

import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
