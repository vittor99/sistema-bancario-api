package com.vittor.sistema_bancario_api.dto;

import java.math.BigDecimal;

public class DepositoDTO {
    BigDecimal valor;

    public DepositoDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}

