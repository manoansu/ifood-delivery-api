package pt.amane.ifooddeliveryapi.domain.repositories;

import pt.amane.ifooddeliveryapi.domain.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {

    List<Usuario> listar();
    Usuario buscar(Long id);
    Usuario salvar(Usuario usuario);
    void remover(Long id);
}
