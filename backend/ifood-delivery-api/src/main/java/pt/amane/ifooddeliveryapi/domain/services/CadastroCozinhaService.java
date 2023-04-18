package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public Cozinha salvar(Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

}
