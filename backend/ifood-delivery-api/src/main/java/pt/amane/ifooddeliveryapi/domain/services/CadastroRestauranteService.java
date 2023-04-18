package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public Restaurante salvar(Restaurante restaurante) {
        return repository.salvar(restaurante);
    }

    public void remover(Long id) {
        try {
            repository.remover(id);

        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
            String.format("Não existe cadstro de restaurante  com código %d ", id));

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format("Restaurante do código %d não pode ser removida, pois está em uso", id));
        }
    }

}
