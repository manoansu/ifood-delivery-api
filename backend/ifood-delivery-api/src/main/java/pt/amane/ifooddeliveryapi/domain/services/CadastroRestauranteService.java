package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.RestauranteNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO = "Restaurante do código %d não pode ser removida, pois está em uso";

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Transactional(readOnly = true)
    public Restaurante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Restaurante create(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinhaService.findById(cozinhaId);

        restaurante.setCozinha(cozinha);

        return repository.save(restaurante);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_RESTAURANTE_EM_USO, id));
        }
    }
}
