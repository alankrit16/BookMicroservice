package com.service.books.Exception;

public class NoRecordFoundException extends RuntimeException{
    String message;

    public NoRecordFoundException(String message) {
        super(message);
    }
}
