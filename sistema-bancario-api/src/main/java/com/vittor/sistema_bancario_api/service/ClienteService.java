package com.vittor.sistema_bancario_api.service;

//import com.vittor.sistema_bancario_api.repository.ClienteRepository;
import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService (ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes (){
        return clienteRepository.findAll();
    }


    public Cliente buscarCliente (Long id){
        return clienteRepository.findById(id).orElseThrow();
    }


    public Cliente atualizarCliente(Long id,Cliente cliente){
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow();

        clienteExistente.atualizarDados(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );

        return clienteRepository.save(clienteExistente);

    }



}
