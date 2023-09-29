package com.gerenciamento.pessoa.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> usuarioNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorResponse = new ErrorDetails(new Date(), status.value(), ex.getMessage(), getRequestPath());
        return ResponseEntity.status(status).body(errorResponse);
    }

    private String getRequestPath() {
        return request.getRequestURI();
    }
}
