package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.service.ContaService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/contas/{ClienteId}")
    public Conta salvarConta (@PathVariable Long ClienteId, @RequestBody Conta conta){
        return contaService.salvarConta(ClienteId, conta);
    }



}
