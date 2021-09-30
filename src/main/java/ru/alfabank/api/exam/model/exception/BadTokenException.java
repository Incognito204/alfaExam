package ru.alfabank.api.exam.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadTokenException extends RuntimeException {

    public BadTokenException(String message) {
        super(message);
    }

}
