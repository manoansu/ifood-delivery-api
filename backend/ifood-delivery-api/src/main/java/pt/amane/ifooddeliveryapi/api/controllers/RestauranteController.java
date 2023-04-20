package pt.amane.ifooddeliveryapi.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroRestauranteService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<Restaurante> findById(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = repository.findById(restauranteId);

        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurante restaurante) {
        try {
            restaurante = service.create(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> update(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
        try {
            Optional<Restaurante> restaurantePersistidoBd = repository.findById(restauranteId);

            if (restaurantePersistidoBd.isPresent()) {
                BeanUtils.copyProperties(restaurante, restaurantePersistidoBd.get(),
                        "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

                Restaurante restauranteSalva = repository.save(restaurantePersistidoBd.get());
                return ResponseEntity.ok(restauranteSalva);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        Optional<Restaurante> restaurantePersistoidoBd = repository.findById(restauranteId);

        if (restaurantePersistoidoBd.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restaurantePersistoidoBd.get());

        return update(restauranteId, restaurantePersistoidoBd.get());
    }

    @DeleteMapping(value = "/{restauranteId}")
    public ResponseEntity<?> delete(@PathVariable Long restauranteId) {

        try {
            service.delete(restauranteId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
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
