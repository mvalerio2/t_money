package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Configuracao;
import com.simulator.tmoney.model.TipoTransacao;
import com.simulator.tmoney.repository.TipoTransacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoTransacaoService {
    
    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    public List<TipoTransacao> findAll() {
        return tipoTransacaoRepository.findAll();
    }

    public TipoTransacao findByTipo(String tipo) {
        return tipoTransacaoRepository.findByTipo(tipo);
    }

    public TipoTransacao findOne(Integer id) {
        return tipoTransacaoRepository.findOne(id);
    }

    public TipoTransacao save(TipoTransacao tipoTransacao) {
        return tipoTransacaoRepository.save(tipoTransacao);
    }

    public TipoTransacao update(TipoTransacao tipoTransacao) {
        return tipoTransacaoRepository.saveAndFlush(tipoTransacao);
    }
}
