package ru.skillbox.janeskills.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServiceException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, Object error) {
        super(message, HttpStatus.BAD_REQUEST, error);
    }

    public BadRequestException(String message, String code) {
        super(message, HttpStatus.BAD_REQUEST, code);
    }

    public BadRequestException(String message, String code, Object error) {
        super(message, HttpStatus.BAD_REQUEST, code, error);
    }

}
