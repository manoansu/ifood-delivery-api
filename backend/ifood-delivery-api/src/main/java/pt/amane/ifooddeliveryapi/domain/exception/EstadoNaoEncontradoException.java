package pt.amane.ifooddeliveryapi.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this("Não existe cadstro de estado  com código %d " + estadoId);
    }
}
