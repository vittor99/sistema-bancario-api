package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.dto.DepositoDTO;
import com.vittor.sistema_bancario_api.dto.SaqueDTO;
import com.vittor.sistema_bancario_api.dto.TransferenciaDTO;
import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }



    @Operation(summary = "salva conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta salva com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })
    @PostMapping("/contas/{clienteId}")
    public Conta salvarConta(@Parameter(description = "ID do cliente para o qual a conta será criada")@PathVariable Long clienteId) {
        return contaService.salvarConta(clienteId);
    }



    @Operation(summary = "Lista todas as contas")
            @ApiResponse(
                    responseCode = "200",
                    description = "Contas listadas com sucesso"
            )

    @GetMapping("/contas")
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }



    @Operation(summary = "Busca conta por numero")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta encontrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta não encontrada"
            )
    }
    )
    @GetMapping("/contas/{numero}")
    public Conta buscarContaPorNumero(@Parameter(description = "Número da conta que será consultada")@PathVariable Long numero) {
        return contaService.buscarContaPorNumero(numero);
    }



    @Operation(summary = "Realiza um depósito na conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Deposito realizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Conta inativa ou valor do depósito inválido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta nao encontrada"
            )
    })
    @PostMapping("/contas/{numero}/deposito")
    public Conta depositar(@Parameter(description = "Número da conta que receberá o depósito")@PathVariable Long numero, @Valid @RequestBody DepositoDTO depositoDTO) {
        return contaService.depositar(numero, depositoDTO.getValor());
    }



    @Operation(summary = "Realiza um saque na conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Saque realizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta nao encontrada"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Saldo insuficiente, valor invalido ou Conta inativa "
            )
    })
    @PostMapping("/contas/{numero}/sacar")
    public Conta sacar(@Parameter(description = "Numero da conta que realizara o saque")@PathVariable Long numero, @Valid @RequestBody SaqueDTO saqueDTO) {
        return contaService.sacar(numero, saqueDTO.getValor());
    }

    @Operation(summary = "Realiza transferencias")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "transferencia realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Valor de transferencia invalido, saldo insuficiente, conta(s) inativa(s), Transferencia invalida"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta nao encontrada"
            )
    })
    @PostMapping("/contas/{numeroOrigem}/transferir/{numeroDestino}")
    public Conta transferir(@Parameter(description = "numero da conta de origem")@PathVariable Long numeroOrigem
                            ,@Parameter(description = "numero da conta de destino")@PathVariable Long numeroDestino,@Valid @RequestBody TransferenciaDTO transferenciaDTO) {
        return contaService.transferir(numeroOrigem, numeroDestino, transferenciaDTO.getValor());
    }




    @Operation(summary = "Deleta conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Conta deletada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta nao encontrada"
            )

}
    )
    @DeleteMapping("/contas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta(@Parameter(description = "ID da conta que será deletada")@PathVariable Long id) {
        contaService.deletarConta(id);
    }



    @Operation(summary = "Altera status da conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta nao encontrada"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Status alterado com sucesso"
            )
}
    )
    @PatchMapping("/contas/{numero}/alterarStatus")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarStatus (@Parameter(description = "Número da conta que terá o status alterado")@PathVariable Long numero){
        contaService.alternarStatus(numero);
    }


}
