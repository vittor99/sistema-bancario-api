package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.dto.ClienteDTO;
import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Salva o cliente")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente salvo com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Nome, CPF, e-mail ou telefone inválido ou não preenchido"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "CPF já cadastrado"
                    )
            }
    )
    @PostMapping("/clientes")
    public Cliente salvar(@Parameter(description = "recebe o cliente") @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(
                clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getEmail(),
                clienteDTO.getTelefone()
        );
        return clienteService.salvar(cliente);
    }


    @Operation(summary = "lista todos os clientes")
    @ApiResponse(
            responseCode = "200",
            description = "Clientes listados com sucesso"
    )
    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @Operation(summary = "Busca cliente por CPF")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado"
                    )
            }
    )
    @GetMapping("/clientes/{cpf}")
    public Cliente buscarClientePorCpf(@PathVariable String cpf) {
        return clienteService.buscarClientePorCpf(cpf);
    }

    @Operation(summary = "Atualiza os dados do cliente")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente atualizado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado"
                    )
            }
    )
    @PutMapping("/clientes/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @Operation(summary = "Deleta o cliente")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Cliente deletado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Não é possível excluir um cliente que possui contas vinculadas"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado"
                    )
            }
    )
    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }

}