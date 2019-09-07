package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.repository.CarteiraRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {
    
    @Autowired
    private CarteiraRepository carteiraRepository;

    /*
    Lista todas as carteiras
     */
    public List<Carteira> findAll(){
        return carteiraRepository.findAll();
    }

    /*
    Carrega a carteira apartir do id
     */
    public Optional<Carteira> findOne(Integer id){
        return carteiraRepository.findById(id);
    }

    /*
    Salva a carteira no banco
     */
    public Carteira save(Carteira carteira){
        return carteiraRepository.save(carteira);
    }

    /*
    Atualiza a carteira no banco
     */
    public Carteira update(Carteira carteira){
        return carteiraRepository.saveAndFlush(carteira);
    }

    /*
    Lista as carteira de um determinado usuario
     */
    public List<Carteira> findByUsuario(Usuario usuario){
        return carteiraRepository.findByUsuarioId(usuario);
    }

    /*
    Deleta a carteira
     */
    public void delete(Integer id){
        carteiraRepository.delete(findOne(id).get());
    }
}
