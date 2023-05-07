package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.Cidade;
import pt.amane.ifooddeliveryapi.domain.entities.Estado;
import pt.amane.ifooddeliveryapi.domain.exception.CidadeNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.repositories.CidadeRepository;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO = "Cidade do código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository repository;
    @Autowired
    private CadastroEstadoService estadoService;

    @Transactional(readOnly = true)
    public Cidade findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Cidade create(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.findById(estadoId);
        cidade.setEstado(estado);

        return repository.save(cidade);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            // Executa/descarrega todas as alterações/mudanças pendente na base de dado.
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_CIDADE_EM_USO, id));
        }
    }
}
