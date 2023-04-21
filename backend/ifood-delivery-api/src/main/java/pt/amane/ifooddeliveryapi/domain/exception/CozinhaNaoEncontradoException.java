package pt.amane.ifooddeliveryapi.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public CozinhaNaoEncontradoException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradoException(Long cozinhaId) {
        this("Não existe cadstro de cozinha  com código %d " + cozinhaId);
    }
}
