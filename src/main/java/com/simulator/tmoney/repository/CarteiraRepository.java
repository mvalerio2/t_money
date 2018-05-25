package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer>{
    
    List<Carteira> findByUsuarioId(Usuario u);
    
}
