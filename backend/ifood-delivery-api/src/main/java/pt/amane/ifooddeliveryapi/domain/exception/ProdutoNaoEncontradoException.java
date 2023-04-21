package pt.amane.ifooddeliveryapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }

    public ProdutoNaoEncontradoException(Long produtoId) {
        this("Não existe cadstro de produto  com código %d " + produtoId);
    }
}
