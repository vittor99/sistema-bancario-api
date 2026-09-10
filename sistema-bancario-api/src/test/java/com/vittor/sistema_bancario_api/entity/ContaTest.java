package com.vittor.sistema_bancario_api.entity;

import com.vittor.sistema_bancario_api.exception.ContaInativaException;
import com.vittor.sistema_bancario_api.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaTest {
    //negativos

    @Test
    void deveRecusarContaSemCliente() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Conta(1000L, null);
        });
    }

    @Test
    void deveRecusarDepositoEmContaInativa (){
        Cliente cliente = new Cliente("teste", "1000", "teste", "32424" );
        Conta conta = new Conta(100L, cliente);
        conta.alterarStatus();
        assertThrows(ContaInativaException.class, () -> {
            conta.depositar(new BigDecimal("500"));
        });
    }

    @Test
    void deveRecusarSaqueEmContaInativa(){
        Cliente cliente = new Cliente("teste", "1000", "teste", "32424" );
        Conta conta = new Conta (100L, cliente);
        conta.alterarStatus();
        assertThrows(ContaInativaException.class, () -> {
            conta.sacar(new BigDecimal("500"));
        });
    }

    @Test
    void deveRecusarSaqueComSaldoInsuficiente (){
        Cliente cliente = new Cliente("teste", "1000", "teste", "32424" );
        Conta conta = new Conta(100L, cliente);

        assertThrows(SaldoInsuficienteException.class, ()-> {
            conta.sacar(new BigDecimal("100"));
        });
    }

    //positivos
    @Test
    void deveDepositarValorValido() {

        // Arrange
        Cliente cliente = new Cliente("teste", "1000", "teste", "32424");
        Conta conta = new Conta(1000L, cliente);

        // Act
        conta.depositar(new BigDecimal("500"));

        // Assert
        assertEquals(new BigDecimal("500"), conta.getSaldo());
    }

    @Test
    void deveSacarValorValido(){
        Cliente cliente = new Cliente("teste", "1000", "teste", "32424" );
        Conta conta = new Conta (100L,cliente);

        conta.depositar(new BigDecimal("500"));
        conta.sacar(new BigDecimal("100"));

        assertEquals(new BigDecimal("400"), conta.getSaldo());

    }

    @Test
    void deveAlterarStatusDaConta(){
        Cliente cliente = new Cliente("teste", "1000", "teste", "32424");
        Conta conta = new Conta(1000L, cliente);

        conta.alterarStatus();

        assertEquals(false, conta.isAtiva());

        conta.alterarStatus();

        assertEquals(true, conta.isAtiva());

    }

    }
