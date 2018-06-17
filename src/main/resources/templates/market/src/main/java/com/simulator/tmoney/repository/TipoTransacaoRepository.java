package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao, Integer>{
    
    
    TipoTransacao findByTipo(String tipo);
}
