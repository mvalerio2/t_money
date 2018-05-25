package com.simulator.tmoney.service;

import com.simulator.tmoney.model.Role;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.repository.RoleRepository;
import com.simulator.tmoney.repository.UsuarioRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findOne(Integer id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setAtivo(Boolean.TRUE);
        List<Usuario> list = usuarioRepository.findAll();
        Role userRole = new Role();
        if (list == null || list.isEmpty()) {
            userRole = roleRepository.findByNomeRole("ADMIN");
        } else {
            userRole = roleRepository.findByNomeRole("USER");
        }
        usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setAtivo(Boolean.TRUE);
        return usuarioRepository.saveAndFlush(usuario);
    }
}
