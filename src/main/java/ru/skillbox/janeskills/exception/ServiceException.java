package ru.skillbox.janeskills.exception;

import static ru.skillbox.janeskills.util.Constant.ERROR;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ServiceException extends RuntimeException {

    private final HttpStatus status;
    private final Object error;
    private final String code;

    protected ServiceException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    protected ServiceException(String message, HttpStatus status) {
        this(message, status, ERROR);
    }

    protected ServiceException(String message, String code) {
        this(message, HttpStatus.BAD_REQUEST, code);
    }

    protected ServiceException(String message, HttpStatus status, Object error) {
        this(message, status, ERROR, error);
    }

    protected ServiceException(String message, HttpStatus status, String code) {
        this(message, status, code, null);
    }

    protected ServiceException(String message, HttpStatus status, String code, Object error) {
        super(message);
        this.status = status;
        this.code = code;
        this.error = error;
    }

}
