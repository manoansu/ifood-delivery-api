package pt.amane.ifooddeliveryapi.domain.repositories;

import org.springframework.stereotype.Repository;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
