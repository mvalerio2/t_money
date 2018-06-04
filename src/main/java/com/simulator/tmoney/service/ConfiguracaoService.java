package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Configuracao;
import com.simulator.tmoney.repository.ConfiguracaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {
    
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    /*
    Lista as configurações do sistema
     */
    public List<Configuracao> findAll() {
        return configuracaoRepository.findAll();
    }

    /*
    Carega uma configuração apartir do id
     */
    public Configuracao findOne(Integer id) {
        return configuracaoRepository.findOne(id);
    }

    /*
    Salva a configuração no banco
     */
    public Configuracao save(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    /*
    Atualiza a configuração
     */
    public Configuracao update(Configuracao configuracao) {
        return configuracaoRepository.saveAndFlush(configuracao);
    }
}
