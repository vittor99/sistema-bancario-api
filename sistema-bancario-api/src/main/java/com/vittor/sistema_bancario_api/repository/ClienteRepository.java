package com.vittor.sistema_bancario_api.repository;


import com.vittor.sistema_bancario_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}