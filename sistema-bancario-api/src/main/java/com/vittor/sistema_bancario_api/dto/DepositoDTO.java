package com.vittor.sistema_bancario_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class DepositoDTO {
    @Schema(description = "valor do deposito maior do que zero")
    @Positive(message = "O valor do depósito deve ser maior que zero")//Para ativar a validação desse DTO no controller, é só adicionar @Valid
    private BigDecimal valor;

    public DepositoDTO(){
    }
    public BigDecimal getValor() {
        return valor;
    }
}

