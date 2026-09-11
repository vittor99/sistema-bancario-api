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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
