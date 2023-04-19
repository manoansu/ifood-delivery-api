package pt.amane.ifooddeliveryapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
