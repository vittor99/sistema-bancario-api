package com.vittor.sistema_bancario_api.service;

import com.vittor.sistema_bancario_api.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    private final ContaRepository contaRepository;


    public ContaService (ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }






}
