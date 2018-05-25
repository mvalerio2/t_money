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
    
    public List<Criptomoeda> findAll(){
        return criptomoedaRepository.findAll();
    }
    
    public Criptomoeda findOne(Integer id){
        return criptomoedaRepository.findOne(id);
    }
    
    public Criptomoeda save(Criptomoeda criptomoeda){
        return criptomoedaRepository.save(criptomoeda);
    }
    
    public Criptomoeda update(Criptomoeda criptomoeda){
        return criptomoedaRepository.saveAndFlush(criptomoeda);
    }

    public Criptomoeda findByNome(String nome){
        return criptomoedaRepository.findByNome(nome);
    }
}
