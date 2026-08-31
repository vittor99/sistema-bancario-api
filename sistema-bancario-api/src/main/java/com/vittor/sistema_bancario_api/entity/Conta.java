package com.vittor.sistema_bancario_api.entity;

import com.vittor.sistema_bancario_api.exception.SaldoInsuficienteException;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    private BigDecimal saldo;
    private boolean ativa;
    @ManyToOne
    private Cliente cliente;


    public Conta() {

    }

    public Conta(Long numero, Cliente cliente) {
        this.numero = numero;
        this.saldo = BigDecimal.ZERO;
        this.ativa = true;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public Long getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar (BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(valor);
        }
    }

    public void sacar(BigDecimal valor){
        if (saldo.compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque");
        }
            saldo = saldo.subtract(valor);

    }
}
