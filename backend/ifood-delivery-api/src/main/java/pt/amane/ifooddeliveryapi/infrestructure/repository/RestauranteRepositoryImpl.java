package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepositoryQueries;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static pt.amane.ifooddeliveryapi.infrestructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static pt.amane.ifooddeliveryapi.infrestructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private RestauranteRepository restauranteRepository;

//    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//
//        var jpql = new StringBuilder();
//        jpql.append("from Restaurante where 0 = 0 ");
//
//        var parametros = new HashMap<String, Object>();
//
//        if (StringUtils.hasLength(nome)) {
//            jpql.append("and nome like :nome ");
//            parametros.put("nome", "%" + nome + "%");
//        }
//
//        if (taxaFreteInicial != null) {
//            jpql.append("and taxaFrete >= :taxaInicial ");
//            parametros.put("taxaInicial", taxaFreteInicial);
//        }
//
//        if (taxaFreteFinal != null) {
//            jpql.append("and taxaFrete <= :taxaFinal ");
//            parametros.put("taxaFinal", taxaFreteFinal);
//        }
//
//        // pega o valor da consulta do tipo restaurante.
//        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//
//        //para cada parametro setarmos o chave e valor
//        parametros.forEach((key, value) -> query.setParameter(key, value));
//        return query.getResultList();
//
//    }

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {


        //CriteriaBuilder é uma interface que funciona como uma fabrica para construção de uma criteriaQuery.,
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        // é uma interface responsavel por montar um estrutura queryo u seja é um construtor de clausulas.
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        //è raiz do restaurante..
        Root<Restaurante> root = criteria.from(Restaurante.class); // from Restaurante

        if (StringUtils.hasLength(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }
        if (taxaFreteInicial != null) {
           predicates.add(builder
                    .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if (taxaFreteInicial != null) {
            predicates.add(builder
                    .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }
        criteria.where(new Predicate[0]);

         //pega o valor da consulta do tipo restaurante.
        TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis()
                .and(comNomeSemelhante(nome)));
    }
}
