package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.HistoricoTransacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoTransacaoRepository extends JpaRepository<HistoricoTransacao, Integer>{
    
    List<HistoricoTransacao> findByCarteiraId(Carteira carteira);
}
