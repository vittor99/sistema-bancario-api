package com.vittor.sistema_bancario_api.dto;

import java.math.BigDecimal;

public class TransferenciaDTO {
    BigDecimal valor;

    public TransferenciaDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
