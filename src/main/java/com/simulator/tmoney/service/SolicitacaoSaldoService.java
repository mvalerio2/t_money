package com.simulator.tmoney.service;

import com.simulator.tmoney.model.SolicitacaoSaldo;
import com.simulator.tmoney.repository.SolicitacaoSaldoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoSaldoService {
    
    @Autowired
    private SolicitacaoSaldoRepository solicitacaoSaldoRepository;

    /*
    Lista todas as solicitações de saldo
     */
    public List<SolicitacaoSaldo> findAll() {
        return solicitacaoSaldoRepository.findAll();
    }

    /*
    Carrega uma solicitação pelo id
     */
    public SolicitacaoSaldo findOne(Integer id) {
        return solicitacaoSaldoRepository.findOne(id);
    }

    /*
    Salva a solicitação
     */
    public SolicitacaoSaldo save(SolicitacaoSaldo solicitacaoSaldo) {
        return solicitacaoSaldoRepository.save(solicitacaoSaldo);
    }

    /*
    Atualiza a solicitação
     */
    public SolicitacaoSaldo update(SolicitacaoSaldo solicitacaoSaldo) {
        return solicitacaoSaldoRepository.saveAndFlush(solicitacaoSaldo);
    }
}
