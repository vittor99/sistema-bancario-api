package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    private final ClienteService clienteService;


        ClienteController (ClienteService clienteService){
            this.clienteService = clienteService;
        }

    @PostMapping("/clientes")
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }
}
