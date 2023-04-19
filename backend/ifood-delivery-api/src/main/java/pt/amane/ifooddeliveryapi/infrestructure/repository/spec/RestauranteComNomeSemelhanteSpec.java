package pt.amane.ifooddeliveryapi.infrestructure.repository.spec;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

    private static final long serialVersionUID = 1L;

    private String nome;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("taxaFrete"), "%" + nome + "%");
    }
}
