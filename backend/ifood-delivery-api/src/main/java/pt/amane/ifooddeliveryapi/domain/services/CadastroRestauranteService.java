package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.*;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.RestauranteNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.RestauranteRepository;

import java.util.List;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO = "Restaurante do código %d não pode ser removida, pois está em uso";

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    public CadastroFormaPagamentoService cadastroFormaPagamentoService;

    public Restaurante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public Restaurante create(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cadastroCozinhaService.findById(cozinhaId);
        Cidade cidade = cadastroCidadeService.findById(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return repository.save(restaurante);
    }

    public void ativar(Long restauranteId) {
        Restaurante restaurantePersistidoBd = findById(restauranteId);
        restaurantePersistidoBd.ativar();
    }

    public void inativar(Long restauranteId) {
        Restaurante restaurantePersistidoBd = findById(restauranteId);
        restaurantePersistidoBd.inativar();
    }

    @Transactional
    public void ativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::inativar);
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findById(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.findById(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findById(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.findById(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restauranteAtual = findById(restauranteId);

        restauranteAtual.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restauranteAtual = findById(restauranteId);

        restauranteAtual.fechar();
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = findById(restauranteId);
        Usuario usuario = cadastroUsuarioService.findById(usuarioId);

        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void associarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = findById(restauranteId);
        Usuario usuario = cadastroUsuarioService.findById(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            // Executa/descarrega todas as alterações/mudanças pendente na base de dado.
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
            String.format(MSG_RESTAURANTE_EM_USO, id));
        }
    }
}
