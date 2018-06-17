package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Criptomoeda;
import com.simulator.tmoney.repository.CriptomoedaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriptomoedaService {
    
    @Autowired
    private CriptomoedaRepository criptomoedaRepository;

    /*
    Lista as criptomoedas cadastradas no sistema
     */
    public List<Criptomoeda> findAll(){
        return criptomoedaRepository.findAll();
    }

    /*
    Carrega uma criptomoeda apartir do id
     */
    public Criptomoeda findOne(Integer id){
        return criptomoedaRepository.findOne(id);
    }

    /*
    Salva a criptomoeda
     */
    public Criptomoeda save(Criptomoeda criptomoeda){
        return criptomoedaRepository.save(criptomoeda);
    }

    /*
    Atualiza a criptomoeda
     */
    public Criptomoeda update(Criptomoeda criptomoeda){
        return criptomoedaRepository.saveAndFlush(criptomoeda);
    }

    /*
    Carrega a criptomoeda pelo nome
     */
    public Criptomoeda findByNome(String nome){
        return criptomoedaRepository.findByNome(nome);
    }
}
