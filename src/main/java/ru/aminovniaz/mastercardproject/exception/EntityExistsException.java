package ru.aminovniaz.mastercardproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class EntityExistsException extends RuntimeException {

    private final String message;

    public EntityExistsException(String message) {
        super();
        this.message = message;
    }
}
