package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Usuario findByEmail(String email);
}
