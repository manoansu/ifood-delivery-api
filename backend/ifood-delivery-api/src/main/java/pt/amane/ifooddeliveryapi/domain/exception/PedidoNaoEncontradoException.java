package pt.amane.ifooddeliveryapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }

    public PedidoNaoEncontradoException(Long pedidoId) {
        this("Não existe cadstro de pedido  com código %d " + pedidoId);
    }
}
