package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Role;
import com.simulator.tmoney.repository.RoleRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /*
    Lista as roles do sistema
     */
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /*
    Carrega uma role pelo id
     */
    public Optional<Role> findOne(Integer id) {
        return roleRepository.findById(id);
    }

    /*
    Salva a role
     */
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /*
    Atualiza a role
     */
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

}
