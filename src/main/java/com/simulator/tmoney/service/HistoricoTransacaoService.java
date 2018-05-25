package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.HistoricoTransacao;
import com.simulator.tmoney.repository.HistoricoTransacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoTransacaoService {
    
    @Autowired
    private HistoricoTransacaoRepository historicoTransacaoRepository;
    
    public List<HistoricoTransacao> findAll(){
        return historicoTransacaoRepository.findAll();
    }
    
    public HistoricoTransacao findOne(Integer id){
        return historicoTransacaoRepository.findOne(id);
    }
    
    public List<HistoricoTransacao> findByCarteiraId(Carteira carteira){
        return historicoTransacaoRepository.findByCarteiraId(carteira);
    }
    
    public HistoricoTransacao save(HistoricoTransacao historicoTransacao){
        return historicoTransacaoRepository.save(historicoTransacao);
    }
    
    public HistoricoTransacao update(HistoricoTransacao historicoTransacao){
        return historicoTransacaoRepository.saveAndFlush(historicoTransacao);
    }
}
