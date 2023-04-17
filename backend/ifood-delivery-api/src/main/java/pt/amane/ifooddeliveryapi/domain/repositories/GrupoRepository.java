package pt.amane.ifooddeliveryapi.domain.repositories;

import pt.amane.ifooddeliveryapi.domain.entities.Grupo;

import java.util.List;

public interface GrupoRepository {

    List<Grupo> listar();
    Grupo buscar(Long id);
    Grupo salvar(Grupo grupo);
    void remover(Grupo grupo);


}
