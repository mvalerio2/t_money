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

    /*
    Lista todos os historicos cadastrados do banco
     */
    public List<HistoricoTransacao> findAll(){
        return historicoTransacaoRepository.findAll();
    }

    /*
    Carrega um historico apartir do id
     */
    public HistoricoTransacao findOne(Integer id){
        return historicoTransacaoRepository.findOne(id);
    }

    /*
    Carrega o historico de uma determinada carteira
     */
    public List<HistoricoTransacao> findByCarteiraId(Carteira carteira){
        return historicoTransacaoRepository.findByCarteiraId(carteira);
    }

    /*
    Salva o Historico
     */
    public HistoricoTransacao save(HistoricoTransacao historicoTransacao){
        return historicoTransacaoRepository.save(historicoTransacao);
    }

    /*
    Atualiza o Historico
     */
    public HistoricoTransacao update(HistoricoTransacao historicoTransacao){
        return historicoTransacaoRepository.saveAndFlush(historicoTransacao);
    }
}
