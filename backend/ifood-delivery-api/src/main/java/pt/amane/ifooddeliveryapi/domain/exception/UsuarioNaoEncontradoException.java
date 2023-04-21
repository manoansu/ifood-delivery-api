package pt.amane.ifooddeliveryapi.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradoException(Long usuarioId) {
        this("Não existe cadstro de usuário  com código %d " + usuarioId);
    }
}
