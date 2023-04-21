package pt.amane.ifooddeliveryapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Long RestauranteId) {
        this("Não existe cadastro de cozinha com código %d" + RestauranteId);
    }
}
