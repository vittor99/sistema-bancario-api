package com.vittor.sistema_bancario_api.exception;

public class ContaInativaException extends RuntimeException {
    public ContaInativaException(String message) {
        super(message);
    }
}
