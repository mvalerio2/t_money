package com.simulator.tmoney.controller;

import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.HistoricoCotacaoService;
import com.simulator.tmoney.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoricoCotacaoMb {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HistoricoCotacaoService service;

    /*
    Esse metodo cria a ligação com a view e manda os objetos:
    - Lista das Criptomoedas
    - Um objeto novo da Carteira
    - Role do usuario logado
    */
    @GetMapping(value = "/admin/historicoCotacao")
    public ModelAndView historicoCotacao() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("historicoCotacoes", service.findHistoricoCotacaosTop100());
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("admin/historicoCotacao");
        return modelAndView;
    }

}
