package pt.amane.ifooddeliveryapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
