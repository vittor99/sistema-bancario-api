package com.vittor.sistema_bancario_api.service;


import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.exception.ClienteNaoEncontradoException;
import com.vittor.sistema_bancario_api.exception.CpfJaCadastradoException;
import com.vittor.sistema_bancario_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()){
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }


    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(()-> new ClienteNaoEncontradoException("Cliente nao encontrado!"));
    }


    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(()-> new ClienteNaoEncontradoException("Cliente nao encontrado!"));

        clienteExistente.atualizarDados(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );

        return clienteRepository.save(clienteExistente);

    }


    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new ClienteNaoEncontradoException("Cliente nao encontrado!"));
        clienteRepository.delete(cliente);
    }


}
