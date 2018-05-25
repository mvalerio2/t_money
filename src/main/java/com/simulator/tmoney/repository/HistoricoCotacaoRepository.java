package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.HistoricoCotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoCotacaoRepository extends JpaRepository<HistoricoCotacao, Integer>{
    
}
