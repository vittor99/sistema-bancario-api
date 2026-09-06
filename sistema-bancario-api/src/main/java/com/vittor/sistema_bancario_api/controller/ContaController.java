package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.dto.DepositoDTO;
import com.vittor.sistema_bancario_api.dto.SaqueDTO;
import com.vittor.sistema_bancario_api.dto.TransferenciaDTO;
import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Operation(summary = "salva conta")
    @PostMapping("/contas/{clienteId}")
    public Conta salvarConta(@Parameter(description = "ID do cliente para o qual a conta será criada")@PathVariable Long clienteId) {
        return contaService.salvarConta(clienteId);
    }

    @Operation(summary = "Lista todas as contas")
    @GetMapping("/contas")
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @Operation(summary = "Busca conta por numero")
    @GetMapping("/contas/{numero}")
    public Conta buscarContaPorNumero(@Parameter(description = "Número da conta que será consultada")@PathVariable Long numero) {
        return contaService.buscarContaPorNumero(numero);
    }

    @Operation(summary = "Realiza um depósito na conta")
    @PostMapping("/contas/{numero}/deposito")
    public Conta depositar(@Parameter(description = "Número da conta que receberá o depósito")@PathVariable Long numero, @Valid @RequestBody DepositoDTO depositoDTO) {
        return contaService.depositar(numero, depositoDTO.getValor());
    }

    @Operation(summary = "Realiza um saque na conta")
    @PostMapping("/contas/{numero}/sacar")
    public Conta sacar(@Parameter(description = "Numero da conta que realizara o saque")@PathVariable Long numero, @Valid @RequestBody SaqueDTO saqueDTO) {
        return contaService.sacar(numero, saqueDTO.getValor());
    }

    @Operation(summary = "Realiza transferencias")
    @PostMapping("/contas/{numeroOrigem}/transferir/{numeroDestino}")
    public Conta transferir(@Parameter(description = "numero da conta de origem")@PathVariable Long numeroOrigem
                            ,@Parameter(description = "numero da conta de destino")@PathVariable Long numeroDestino,@Valid @RequestBody TransferenciaDTO transferenciaDTO) {
        return contaService.transferir(numeroOrigem, numeroDestino, transferenciaDTO.getValor());
    }

    @Operation(summary = "Deleta conta")
    @DeleteMapping("/contas/{id}")
    public void deletarConta(@Parameter(description = "ID da conta que será deletada")@PathVariable Long id) {
        contaService.deletarConta(id);
    }
    @Operation(summary = "Altera status da conta")
    @PatchMapping("/contas/{numero}/alterarStatus")
    public void alterarStatus (@Parameter(description = "Número da conta que terá o status alterado")@PathVariable Long numero){
        contaService.alternarStatus(numero);
    }


}
