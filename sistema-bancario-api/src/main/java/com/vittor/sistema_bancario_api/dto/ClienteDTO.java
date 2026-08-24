package com.vittor.sistema_bancario_api.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;
    @NotBlank(message = "O email é obrigatório")
    private String email;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;


    public ClienteDTO() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
