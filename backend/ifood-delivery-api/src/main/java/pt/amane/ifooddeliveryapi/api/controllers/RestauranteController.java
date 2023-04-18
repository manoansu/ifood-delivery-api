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

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroRestauranteService service;

    @GetMapping
    public List<Restaurante> listar() {
        return repository.listar();
    }

    @GetMapping(value = "/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = repository.buscar(restauranteId);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = service.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
        try {
            Restaurante restaurantePersistoidoBd = repository.buscar(restauranteId);

            if (restaurantePersistoidoBd != null) {
                BeanUtils.copyProperties(restaurante, restaurantePersistoidoBd, "id");

                restaurantePersistoidoBd = repository.salvar(restaurantePersistoidoBd);
                return ResponseEntity.ok(restaurantePersistoidoBd);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        Restaurante restaurantePersistoidoBd = repository.buscar(restauranteId);

        if (restaurantePersistoidoBd == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restaurantePersistoidoBd);

        return atualizar(restauranteId, restaurantePersistoidoBd);
    }

    @DeleteMapping(value = "/{restauranteId}")
    public ResponseEntity<?> remover(@PathVariable Long restauranteId) {

        try {
            service.remover(restauranteId);
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
