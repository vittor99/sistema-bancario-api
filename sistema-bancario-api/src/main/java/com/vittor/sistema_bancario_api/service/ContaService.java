package com.vittor.sistema_bancario_api.service;

import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.exception.ClienteNaoEncontradoException;
import com.vittor.sistema_bancario_api.exception.ContaNaoEncontradoException;
import com.vittor.sistema_bancario_api.exception.TransferenciaInvalidaException;
import com.vittor.sistema_bancario_api.repository.ClienteRepository;
import com.vittor.sistema_bancario_api.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Conta salvarConta(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(()-> new ClienteNaoEncontradoException("Cliente nao encontrado!"));

        Long maiorNumero = contaRepository.buscarMaiorNumero();
        Long proximoNumero;

        if (maiorNumero == null){
            proximoNumero = 1000L;
        }else{
            proximoNumero = maiorNumero + 1;
        }

        Conta conta = new Conta(proximoNumero, cliente);
        return contaRepository.save(conta);

    }

    public List<Conta> listarContas (){
        return contaRepository.findAll();
    }


    public Conta buscarContaPorNumero(Long numero){
        return contaRepository.findByNumero(numero).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));
    }

    public Conta depositar (Long numero, BigDecimal valor){
        Conta conta= contaRepository.findByNumero(numero).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));
        conta.depositar(valor);
        contaRepository.save(conta);
        return conta;
    }

    public Conta sacar (Long numero, BigDecimal valor){
        Conta conta = contaRepository.findByNumero(numero).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));
        conta.sacar(valor);
        contaRepository.save(conta);
        return conta;
    }

    @Transactional
    public Conta transferir (Long numeroOrigem, Long numeroDestino, BigDecimal valor){
        if (numeroOrigem.equals(numeroDestino)){
            throw new TransferenciaInvalidaException("Não é possível transferir para a mesma conta");
        }
        Conta contaOrigem = contaRepository.findByNumero(numeroOrigem).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));
        Conta contaDestino = contaRepository.findByNumero(numeroDestino).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));

        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        return contaOrigem;
    }

    public void deletarConta (Long id){
        Conta conta = contaRepository.findById(id).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));
        contaRepository.delete(conta);
    }

    public void alternarStatus (Long numero){
        Conta conta = contaRepository.findByNumero(numero).orElseThrow(()-> new ContaNaoEncontradoException("Conta nao encontrada!"));
        conta.alterarStatus();
        contaRepository.save(conta);

    }





}
