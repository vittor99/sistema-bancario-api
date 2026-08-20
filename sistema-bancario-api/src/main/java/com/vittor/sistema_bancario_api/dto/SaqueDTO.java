package com.vittor.sistema_bancario_api.dto;

import java.math.BigDecimal;

public class SaqueDTO {
    BigDecimal valor;

    public SaqueDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
