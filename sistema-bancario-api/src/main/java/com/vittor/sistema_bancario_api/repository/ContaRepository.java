package com.vittor.sistema_bancario_api.repository;

import com.vittor.sistema_bancario_api.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long >{

    Optional<Conta> findByNumero(Long numero);

    @Query("SELECT MAX(c.numero) FROM Conta c")
    Long buscarMaiorNumero();



}
