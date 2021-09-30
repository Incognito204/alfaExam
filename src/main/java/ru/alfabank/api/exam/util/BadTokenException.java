package ru.alfabank.api.exam.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid currency token")
public class BadTokenException extends RuntimeException {

    public BadTokenException(String message) {
        super(message);
    }

}
