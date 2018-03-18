package org.meli.exercise.Meli.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class InvalidDnaException extends RuntimeException{
    public InvalidDnaException(String message){
        super(message);
    }
}
