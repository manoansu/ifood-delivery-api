package pt.amane.ifooddeliveryapi.infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.repositories.filters.PedidoFilter;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
        return (root, query, builder) ->{

            //Fetch resolve a consulta na bd de n + 1, usando o fecth possibilita que a consulta seja feita uma unica vez.
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");

            var predicates = new ArrayList<Predicate>();

            if (filter.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
            }
            if (filter.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
            }
            if (filter.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
            }
            if (filter.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("datacracao"), filter.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
