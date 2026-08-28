package com.vittor.sistema_bancario_api.dto;

import com.vittor.sistema_bancario_api.validation.ValidCpf;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;
    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(
            regexp = "\\d{11}",
            message = "O CPF deve conter 11 números"
    ) //para receber apenas numeros
    @ValidCpf
    private String cpf;
    @NotBlank(message = "O email é obrigatório")
    @Email(message ="O email deve ser válido")
    private String email;
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
            regexp = "\\d{10,11}",
            message = "O telefone deve conter 10 ou 11 números"
    )
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
