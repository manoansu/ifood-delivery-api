package pt.amane.ifooddeliveryapi.domain.repositories;

import org.springframework.stereotype.Repository;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    List<Cozinha> findAllByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome);

}
