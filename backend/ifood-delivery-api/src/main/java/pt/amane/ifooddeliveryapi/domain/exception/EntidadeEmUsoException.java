package pt.amane.ifooddeliveryapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) //, reason ="Entidadde não encontrada")
public class EntidadeEmUsoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String message) {
        super(message);
    }

    public EntidadeEmUsoException(Throwable cause) {
        super(cause);
    }
}
