package com.simulator.tmoney.service;

import com.simulator.tmoney.model.HistoricoCotacao;
import com.simulator.tmoney.repository.HistoricoCotacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoCotacaoService {
    
    @Autowired
    private HistoricoCotacaoRepository historicoCotacaoRepository;
    
    public List<HistoricoCotacao> findAll(){
        return historicoCotacaoRepository.findAll();
    }
    
    public HistoricoCotacao findOne(Integer id){
        return historicoCotacaoRepository.findOne(id);
    }
    
    public HistoricoCotacao save(HistoricoCotacao historicoCotacao){
        return historicoCotacaoRepository.save(historicoCotacao);
    }
    
    public HistoricoCotacao update(HistoricoCotacao historicoCotacao){
        return historicoCotacaoRepository.saveAndFlush(historicoCotacao);
    }
}
