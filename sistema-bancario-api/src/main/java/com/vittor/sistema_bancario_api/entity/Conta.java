package com.vittor.sistema_bancario_api.entity;

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

    public Conta(Long numero, BigDecimal saldo, boolean ativa, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.ativa = ativa;
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

    public void definirCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
