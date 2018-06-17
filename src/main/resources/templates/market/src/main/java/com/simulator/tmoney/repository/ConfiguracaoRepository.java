package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Integer>{
    
}
