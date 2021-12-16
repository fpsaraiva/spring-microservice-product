package dev.fpsaraiva.microserviceproduct.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    public BusinessException(String s, HttpStatus code) {
        super(s);
    }
}