package pt.amane.ifooddeliveryapi.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public FormaPagamentoNaoEncontradoException(String message) {
        super(message);
    }

    public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
        this("Não existe cadstro de forma de pagamento  com código %d " + formaPagamentoId);
    }
}
