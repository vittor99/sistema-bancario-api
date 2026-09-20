package com.vittor.sistema_bancario_api.service;

import com.vittor.sistema_bancario_api.entity.Cliente;
import com.vittor.sistema_bancario_api.entity.Conta;
import com.vittor.sistema_bancario_api.exception.ContaNaoEncontradoException;
import com.vittor.sistema_bancario_api.repository.ClienteRepository;
import com.vittor.sistema_bancario_api.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yaml.snakeyaml.events.Event;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    void deveBuscarContaPorNumero() {

        // Arrange
        Cliente cliente = new Cliente("teste", "100", "teste", "32424");
        Conta conta = new Conta(100L, cliente);

        when(contaRepository.findByNumero(100L))
                .thenReturn(Optional.of(conta));

        // Act
        Conta resultado = contaService.buscarContaPorNumero(100L);

        // Assert
        assertEquals(conta, resultado);
    }

    @Test
    void deveSalvarConta (){
        Cliente cliente = new Cliente("teste", "100", "teste", "32424");

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(contaRepository.buscarMaiorNumero())
                .thenReturn(1000L);

        when(contaRepository.save(any(Conta.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Conta resultado = contaService.salvarConta(1L);

        assertEquals(1001L, resultado.getNumero());

    }

    @Test
    void deveListarContas (){
        Cliente cliente = new Cliente("teste", "100", "teste", "32424");

        Conta conta1 = new Conta(100L, cliente);
        Conta conta2 = new Conta(1001L, cliente);

        List<Conta> contas = List.of(conta1, conta2);

        when(contaRepository.findAll()).thenReturn(contas);

        List<Conta> resultado = contaService.listarContas();

        assertEquals(contas,resultado);


    }

     @Test
     void deveDepositar (){
        Cliente cliente = new Cliente("teste", "100", "teste", "32424");
        Conta conta = new Conta(100L, cliente);

         when(contaRepository.findByNumero(100L))
                 .thenReturn(Optional.of(conta));

       conta = contaService.depositar(100L,new BigDecimal("500"));

       assertEquals(new BigDecimal("500"), conta.getSaldo());

     }

     @Test
     void deveSacar (){
        Cliente cliente = new Cliente("teste", "100", "teste", "32424");
        Conta conta = new Conta(100L, cliente);

        when(contaRepository.findByNumero(100L)).thenReturn(Optional.of(conta));

         conta.depositar(new BigDecimal("200"));

        conta = contaService.sacar(100L, new BigDecimal("100"));

        assertEquals(new BigDecimal("100"), conta.getSaldo());

     }

     @Test
     void deveTransferir (){
        Cliente cliente = new Cliente ("teste", "100", "teste", "32424");
        Conta conta1 = new Conta (100L, cliente);
        Conta conta2 = new Conta (101L, cliente);

        when (contaRepository.findByNumero(100L)).thenReturn(Optional.of(conta1));
        when (contaRepository.findByNumero(101L)).thenReturn(Optional.of(conta2));

        conta1.depositar(new BigDecimal("100"));
        conta2.depositar(new BigDecimal("100"));

        conta1 = contaService.transferir(100L,101L, new BigDecimal("50"));

        assertEquals(new BigDecimal("50"), conta1.getSaldo());
        assertEquals(new BigDecimal("150"), conta2.getSaldo());

     }



    //negativos

    @Test
    void deveRecusarBuscaDeContaInexistente() {

        // Arrange
        when(contaRepository.findByNumero(9999L))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ContaNaoEncontradoException.class, () -> {
            contaService.buscarContaPorNumero(9999L);
        });

    }

}
