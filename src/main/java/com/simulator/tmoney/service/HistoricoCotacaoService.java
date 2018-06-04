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

    /*
    Lista o historico da cotação
     */
    public List<HistoricoCotacao> findAll(){
        return historicoCotacaoRepository.findAll();
    }

    /*
    Carrega um historico de cotação apartir do id
     */
    public HistoricoCotacao findOne(Integer id){
        return historicoCotacaoRepository.findOne(id);
    }

    /*
    Salva o historico
     */
    public HistoricoCotacao save(HistoricoCotacao historicoCotacao){
        return historicoCotacaoRepository.save(historicoCotacao);
    }

    /*
    Atualiza o historico
     */
    public HistoricoCotacao update(HistoricoCotacao historicoCotacao){
        return historicoCotacaoRepository.saveAndFlush(historicoCotacao);
    }
}
