package com.vittor.sistema_bancario_api.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class SaqueDTO {
    @Positive (message = "O valor do saque deve ser maior que zero")
    private BigDecimal valor;

    public SaqueDTO(){

    }


    public BigDecimal getValor() {
        return valor;
    }
}
