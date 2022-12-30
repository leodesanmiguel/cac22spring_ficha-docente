package com.leodesanmiguel.fichadocente.exceptions;


import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
