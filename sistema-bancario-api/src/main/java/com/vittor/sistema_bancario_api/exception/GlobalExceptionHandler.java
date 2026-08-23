package com.vittor.sistema_bancario_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<?> tratarCpfJaCadastrado(CpfJaCadastradoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("erro", exception.getMessage()));

    }

}