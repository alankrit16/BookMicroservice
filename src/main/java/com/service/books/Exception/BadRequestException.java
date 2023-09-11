package com.service.books.Exception;

public class BadRequestException extends RuntimeException{
    String message;

    public BadRequestException(String message) {
        super(message);
    }
}
