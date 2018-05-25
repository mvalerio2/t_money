package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.SolicitacaoSaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoSaldoRepository extends JpaRepository<SolicitacaoSaldo, Integer>{
    
}
