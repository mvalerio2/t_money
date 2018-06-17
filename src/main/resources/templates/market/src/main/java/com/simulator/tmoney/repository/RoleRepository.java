package com.simulator.tmoney.repository;

import com.simulator.tmoney.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
    Role findByNomeRole(String role);
}
