package com.simulator.tmoney.service;

import com.simulator.tmoney.model.TipoTransacao;
import com.simulator.tmoney.repository.TipoTransacaoRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoTransacaoService {
    
    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    /*
    Lista todos os tipos de Transações
     */
    public List<TipoTransacao> findAll() {
        return tipoTransacaoRepository.findAll();
    }

    /*
    Carrega o tipo de transação pelo tipo
     */
    public TipoTransacao findByTipo(String tipo) {
        return tipoTransacaoRepository.findByTipo(tipo);
    }

    /*
    Carrega o tipo de transação pelo id
     */
    public Optional<TipoTransacao> findOne(Integer id) {
        return tipoTransacaoRepository.findById(id);
    }

    /*
    Salva o tipo da transação
     */
    public TipoTransacao save(TipoTransacao tipoTransacao) {
        return tipoTransacaoRepository.save(tipoTransacao);
    }

    /*
    Atualiza o tipo da transação
     */
    public TipoTransacao update(TipoTransacao tipoTransacao) {
        return tipoTransacaoRepository.saveAndFlush(tipoTransacao);
    }
}
