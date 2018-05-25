package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Role;
import com.simulator.tmoney.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findOne(Integer id) {
        return roleRepository.findOne(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

}
