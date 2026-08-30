package com.vittor.sistema_bancario_api.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class DepositoDTO {
    @Positive(message = "O valor do depósito deve ser maior que zero")//Para ativar a validação desse DTO no controller, é só adicionar @Valid
    private BigDecimal valor;

    public DepositoDTO(){

    }

    public DepositoDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}

