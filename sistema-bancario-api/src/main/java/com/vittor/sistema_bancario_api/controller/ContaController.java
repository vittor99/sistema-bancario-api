package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.dto.DepositoDTO;
import com.vittor.sistema_bancario_api.dto.SaqueDTO;
import com.vittor.sistema_bancario_api.dto.TransferenciaDTO;
import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/contas/{clienteId}")
    public Conta salvarConta(@PathVariable Long clienteId) {
        return contaService.salvarConta(clienteId);
    }

    @GetMapping("/contas")
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/contas/{numero}")
    public Conta buscarContaPorNumero(@PathVariable Long numero) {
        return contaService.buscarContaPorNumero(numero);
    }

    @PostMapping("/contas/{numero}/deposito")
    public Conta depositar(@PathVariable Long numero, @Valid @RequestBody DepositoDTO depositoDTO) {
        return contaService.depositar(numero, depositoDTO.getValor());
    }

    @PostMapping("/contas/{numero}/sacar")
    public Conta sacar(@PathVariable Long numero, @Valid @RequestBody SaqueDTO saqueDTO) {
        return contaService.sacar(numero, saqueDTO.getValor());
    }

    @PostMapping("/contas/{numeroOrigem}/transferir/{numeroDestino}")
    public Conta transferir(@Valid @PathVariable Long numeroOrigem, @PathVariable Long numeroDestino, @RequestBody TransferenciaDTO transferenciaDTO) {//Para ativar a validação desse DTO no controller, é só adicionar @Valid
        return contaService.transferir(numeroOrigem, numeroDestino, transferenciaDTO.getValor());
    }

    @DeleteMapping("/contas/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
    }


}
