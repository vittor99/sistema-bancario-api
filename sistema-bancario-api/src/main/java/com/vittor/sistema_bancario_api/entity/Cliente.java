package com.vittor.sistema_bancario_api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {
    @Id//"O atributo id é a identificação principal dessa entidade."
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas;


    public Cliente(){

    }

    public Cliente (String nome, String cpf, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail(){
        return email;
    }

    public String getTelefone(){
        return telefone;
    }

    public void atualizarDados(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }


}

