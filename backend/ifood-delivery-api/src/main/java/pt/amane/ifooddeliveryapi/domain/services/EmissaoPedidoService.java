package pt.amane.ifooddeliveryapi.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.amane.ifooddeliveryapi.domain.entities.*;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.NegocioException;
import pt.amane.ifooddeliveryapi.domain.exception.PedidoNaoEncontradoException;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;

@Service
public class EmissaoPedidoService {

    private static final String MSG_PEDIDO_EM_USO = "Pedido do código %d não pode ser removida, pois está em uso";

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return repository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.findById(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuario.findById(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestaurante.findById(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamento.findById(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProduto.findById(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    public Pedido findById(String codigoPedidoId) {
        return repository.findByCodigo(codigoPedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedidoId));
    }

//    @Transactional(readOnly = true)
//    public void delete(Long id) {
//        try {
//            repository.deleteById(id);
//            // Executa/descarrega todas as alterações/mudanças pendente na base de dado.
//            repository.flush();
//
//        }catch (EmptyResultDataAccessException e) {
//            throw new PedidoNaoEncontradoException(id);
//
//        }catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(
//            String.format(MSG_PEDIDO_EM_USO, id));
//        }
//    }
}
