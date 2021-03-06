package com.simulator.tmoney.service;

import com.simulator.tmoney.model.HistoricoCotacao;
import com.simulator.tmoney.repository.HistoricoCotacaoRepository;
import java.util.List;
import java.util.Optional;

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
    Lista o historico da cotação com limit 100
     */
    public List<HistoricoCotacao> findHistoricoCotacaosTop100(){
        return historicoCotacaoRepository.findTop100ByOrderByDataHoraDesc();
    }

    /*
    Carrega um historico de cotação apartir do id
     */
    public Optional<HistoricoCotacao> findOne(Integer id){
        return historicoCotacaoRepository.findById(id);
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
