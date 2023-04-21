package pt.amane.ifooddeliveryapi.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public GrupoNaoEncontradoException(String message) {
        super(message);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this("Não existe cadstro de grupo  com código %d " + grupoId);
    }
}
