package pt.amane.ifooddeliveryapi.infrastructure.repository.spec;


import org.springframework.data.jpa.domain.Specification;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestauranteComFreteGratisSpec  implements Specification<Restaurante> {

    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get("tafaFrete"), BigDecimal.ZERO);
    }
}
