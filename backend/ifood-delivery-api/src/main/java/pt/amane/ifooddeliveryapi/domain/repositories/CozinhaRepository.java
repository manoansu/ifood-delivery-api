package pt.amane.ifooddeliveryapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

//    List<Cozinha> listrPorNome(String nome);

}
