package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.Criptomoeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriptomoedaRepository extends JpaRepository<Criptomoeda, Integer>{

    Criptomoeda findByNome(String nome);
}
