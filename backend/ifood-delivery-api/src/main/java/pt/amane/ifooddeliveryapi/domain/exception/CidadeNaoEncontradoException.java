package pt.amane.ifooddeliveryapi.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradoException(String message) {
        super(message);
    }

    public CidadeNaoEncontradoException(Long cidadeId) {
        this("Não existe cadstro de estado  com código %d " + cidadeId);
    }
}
