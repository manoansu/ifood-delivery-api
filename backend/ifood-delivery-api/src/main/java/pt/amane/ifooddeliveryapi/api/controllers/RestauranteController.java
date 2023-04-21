package pt.amane.ifooddeliveryapi.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;
import pt.amane.ifooddeliveryapi.domain.exception.RestauranteNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroRestauranteService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroRestauranteService service;

    @GetMapping
    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{restauranteId}")
    public Restaurante findById(@PathVariable Long restauranteId) {
        return service.findById(restauranteId);
    }

    @PostMapping
    public Restaurante create(@RequestBody Restaurante restaurante) {
        try {
            return service.create(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante update(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {

        Restaurante restaurantePersistidoBd = service.findById(restauranteId);

        BeanUtils.copyProperties(restaurante, restaurantePersistidoBd,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return service.create(restaurantePersistidoBd);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante partialUpdate(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Restaurante restaurantePersistoidoBd = service.findById(restauranteId);

        merge(campos, restaurantePersistoidoBd);

        try {
            return update(restauranteId, restaurantePersistoidoBd);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{restauranteId}")
    public void delete(@PathVariable Long restauranteId) {
        service.delete(restauranteId);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        //Resonsavel de converter objeto java em JSON vice versa.
        ObjectMapper objectMapper = new ObjectMapper();

        // cria pra min uma instancia do tipo restaurante usando como base esse mapa com os dadosOrigem
        // ele vai criar uma instacia chamado restauranteOrigem.
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        //dadosOrigem é percorrido para saber o que é q consumidor de API passou para ser atualizado
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

            // findField retorna uma instancia de um campo..
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            //getField busca o valor do campo..
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
