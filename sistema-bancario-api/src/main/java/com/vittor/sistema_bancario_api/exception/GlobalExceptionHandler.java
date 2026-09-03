package com.vittor.sistema_bancario_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<?> tratarCpfJaCadastrado(CpfJaCadastradoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("erro", exception.getMessage()));

    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<?> tratarSaldoInsuficiente(SaldoInsuficienteException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", exception.getMessage()));
    }


    @ExceptionHandler(ContaInativaException.class)
        public ResponseEntity<?> tratarContaInativa(ContaInativaException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", exception.getMessage()));
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<?> tratarClienteNaoEncontrado (ClienteNaoEncontradoException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", exception.getMessage()));
    }

    @ExceptionHandler(ContaNaoEncontradoException.class)
    public ResponseEntity<?> tratarContaNaoEncontrada (ContaNaoEncontradoException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro",exception.getMessage()));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErroValidacao(MethodArgumentNotValidException exception) {

        Map<String, String> erros = new HashMap<>();

        for (FieldError erro : exception.getBindingResult().getFieldErrors()) {
            erros.put(
                    erro.getField(),
                    erro.getDefaultMessage()
            );
        }
        return ResponseEntity.badRequest().body(erros);

    }


}