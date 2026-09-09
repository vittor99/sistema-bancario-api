package com.vittor.sistema_bancario_api.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContaTest {

    @Test
    void deveDepositarValorValido() {

        // Arrange
        Conta conta = new Conta(1000L, null);

        // Act
        conta.depositar(new BigDecimal("500"));

        // Assert
        assertEquals(new BigDecimal("500"), conta.getSaldo());
    }
}
