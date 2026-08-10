package com.vittor.sistema_bancario_api.controller;

import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/clientes")
    public List<Cliente> listarClientes (){
        return clienteService.listarClientes();
    }

    @GetMapping("/clientes/{id}")
    public Cliente buscarCliente (@PathVariable Long id){
        return clienteService.buscarCliente(id);
    }




}
