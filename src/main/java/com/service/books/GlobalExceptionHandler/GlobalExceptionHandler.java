package com.service.books.GlobalExceptionHandler;

import com.service.books.Exception.BadRequestException;
import com.service.books.Response.GlobalResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class, ClassCastException.class})
    public ResponseEntity<GlobalResponse> handler(RuntimeException exception){
        GlobalResponse error = new GlobalResponse();
        error.setErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<GlobalResponse> decodeError(RuntimeException exception){
        GlobalResponse error = new GlobalResponse();
        error.setErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }



}
