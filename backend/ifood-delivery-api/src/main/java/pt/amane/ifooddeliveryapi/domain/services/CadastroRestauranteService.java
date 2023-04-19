package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }

    public void remover(Long id) {
        try {
            restauranteRepository.remover(id);

        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
            String.format("Não existe cadstro de restaurante  com código %d ", id));

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format("Restaurante do código %d não pode ser removida, pois está em uso", id));
        }
    }

}
