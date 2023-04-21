package pt.amane.ifooddeliveryapi.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public PermissaoNaoEncontradoException(String message) {
        super(message);
    }

    public PermissaoNaoEncontradoException(Long permissaoId) {
        this("Não existe cadstro de permissao  com código %d " + permissaoId);
    }
}
