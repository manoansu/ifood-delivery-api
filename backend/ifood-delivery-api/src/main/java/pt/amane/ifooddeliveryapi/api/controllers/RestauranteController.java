package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.RestauranteDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.RestauranteInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.RestauranteDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.RestauranteInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.exception.CozinhaNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroRestauranteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroRestauranteService service;
    @Autowired
    private RestauranteDTOAssembler restauranteDTOAssembler;

    @Autowired
    private RestauranteInputDataDisassembler restauranteInputDataDisassembler;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public List<RestauranteDTO> findAll() {
        return restauranteDTOAssembler.toCollectionModel(repository.findAll());
    }

    @GetMapping(value = "/{restauranteId}")
    public RestauranteDTO findById(@PathVariable Long restauranteId) {
        Restaurante restaurante =  service.findById(restauranteId);

        return restauranteDTOAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO create(@RequestBody @Valid RestauranteInputData restauranteInputData) {
        try {
            Restaurante restaurante = restauranteInputDataDisassembler.toDomainObject(restauranteInputData);
            return restauranteDTOAssembler.toModel(service.create(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO update(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInputData restauranteInputData) {

        Restaurante restaurantePersistidoBd = service.findById(restauranteId);

//        Restaurante restaurante = restauranteInputDataDisassembler.toDomainObject(restauranteInputData);

//        BeanUtils.copyProperties(restaurante, restaurantePersistidoBd,
//                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        restauranteInputDataDisassembler.copyToDomainObject(restauranteInputData, restaurantePersistidoBd);

        try {
            return restauranteDTOAssembler.toModel(service.create(restaurantePersistidoBd));
        } catch (CozinhaNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{restauranteId}")
    public void delete(@PathVariable Long restauranteId) {
        service.delete(restauranteId);
    }


    //    @PatchMapping("/{restauranteId}")
//    public RestauranteDTO partialUpdate(@PathVariable Long restauranteId,
//                                     @RequestBody Map<String, Object> campos , HttpServletRequest request) {
//        Restaurante restaurantePersistoidoBd = service.findById(restauranteId);
//
//        merge(campos, restaurantePersistoidoBd, request);
//        validate(restaurantePersistoidoBd, "restaurante");
//
//        return update(restauranteId, restaurantePersistoidoBd);
//    }
//
//    private void validate(Restaurante restaurante, String objectName) {
//        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
//        validator.validate(restaurante, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidacaoException(bindingResult);
//        }
//    }

    //    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
//
//        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
//
//        try {
//
//            //Resonsavel de converter objeto java em JSON vice versa.
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//
//            // cria pra min uma instancia do tipo restaurante usando como base esse mapa com os dadosOrigem
//            // ele vai criar uma instacia chamado restauranteOrigem.
//            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//
//            //dadosOrigem é percorrido para saber o que é q consumidor de API passou para ser atualizado
//            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//
//                // findField retorna uma instancia de um campo..
//                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//                field.setAccessible(true);
//
//                //getField busca o valor do campo..
//                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
////			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
//
//                ReflectionUtils.setField(field, restauranteDestino, novoValor);
//            });
//        }catch (IllegalArgumentException e) {
//            Throwable rootCause = ExceptionUtils.getRootCause(e);
//            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
//        }
//    }
}
