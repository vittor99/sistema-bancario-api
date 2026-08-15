package com.vittor.sistema_bancario_api.repository;

import com.vittor.sistema_bancario_api.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long >{
}
