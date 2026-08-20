package com.vittor.sistema_bancario_api.service;

import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.repository.ClienteRepository;
import com.vittor.sistema_bancario_api.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService (ContaRepository contaRepository, ClienteRepository clienteRepository){//revisar
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Conta salvarConta(Long clienteId, Conta conta) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();

        conta.definirCliente(cliente);

        return contaRepository.save(conta);

    }

    public List<Conta> listarContas (){
        return contaRepository.findAll();
    }


    public Conta buscarContaPorNumero(Long numero){
        return contaRepository.findByNumero(numero).orElseThrow();
    }

    public Conta depositar (Long numero, BigDecimal valor){
        Conta conta= contaRepository.findByNumero(numero).orElseThrow();
        conta.depositar(valor);
        contaRepository.save(conta);
        return conta;
    }

    public Conta sacar (Long numero, BigDecimal valor){
        Conta conta = contaRepository.findByNumero(numero).orElseThrow();
        conta.sacar(valor);
        contaRepository.save(conta);
        return conta;
    }





}
