package com.vittor.sistema_bancario_api.exception;

public class ContaNaoEncontradoException extends RuntimeException {
    public ContaNaoEncontradoException(String message) {
        super(message);
    }
}
