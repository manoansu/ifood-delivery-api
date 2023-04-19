package pt.amane.ifooddeliveryapi.infrestructure.repository;

import org.springframework.stereotype.Repository;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepositoryQueries;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

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
        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
        criteriaQuery.from(Restaurante.class); // from Restaurante

         //pega o valor da consulta do tipo restaurante.
        TypedQuery<Restaurante> query = manager.createQuery(criteriaQuery);

        return query.getResultList();

    }
}
