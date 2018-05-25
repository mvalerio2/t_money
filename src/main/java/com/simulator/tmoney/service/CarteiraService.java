package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.repository.CarteiraRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {
    
    @Autowired
    private CarteiraRepository carteiraRepository;
    
    public List<Carteira> findAll(){
        return carteiraRepository.findAll();
    }
    
    public Carteira findOne(Integer id){
        return carteiraRepository.findOne(id);
    }
    
    public Carteira save(Carteira carteira){
        return carteiraRepository.save(carteira);
    }
    
    public Carteira update(Carteira carteira){
        return carteiraRepository.saveAndFlush(carteira);
    }
    
    public List<Carteira> findByUsuario(Usuario usuario){
        return carteiraRepository.findByUsuarioId(usuario);
    }
    
    public void delete(Integer id){
        carteiraRepository.delete(id);
    }
}
