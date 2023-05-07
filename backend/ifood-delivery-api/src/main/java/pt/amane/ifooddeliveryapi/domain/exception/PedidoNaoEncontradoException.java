package pt.amane.ifooddeliveryapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String codigoPedidoId) {
        super("Não existe cadstro de pedido  com código %s " + codigoPedidoId);
    }
}
