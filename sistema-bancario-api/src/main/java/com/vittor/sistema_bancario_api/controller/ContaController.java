package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/contas")
    public List<Conta> listarContas (){
        return contaService.listarContas();
    }

    @GetMapping("/contas/{numero}")
    public Conta buscarContaPorNumero(@PathVariable Long numero){
        return contaService.buscarContaPorNumero(numero);
    }




}
