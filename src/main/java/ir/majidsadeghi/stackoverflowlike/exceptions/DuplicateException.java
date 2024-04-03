package ir.majidsadeghi.stackoverflowlike.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException {
    String message;

    public DuplicateException(String message) {
        super(message);
        this.message = message;
    }
}
