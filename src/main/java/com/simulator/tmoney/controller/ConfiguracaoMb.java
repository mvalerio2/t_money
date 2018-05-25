package com.simulator.tmoney.controller;

import com.simulator.tmoney.model.Configuracao;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.ConfiguracaoService;
import com.simulator.tmoney.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfiguracaoMb {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @RequestMapping(value = "/admin/configuracao", method = RequestMethod.GET)
    public ModelAndView criarConfiguracao() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        List<Configuracao> configuracaoList = configuracaoService.findAll();
        if (!configuracaoList.isEmpty()) {
            modelAndView.addObject("configuracao", configuracaoList.iterator().next());
        } else {
            modelAndView.addObject("configuracao", new Configuracao());
        }
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("admin/configuracao");
        return modelAndView;
    }

    @PostMapping("/admin/configuracao/save")
    public ModelAndView save(@Valid Configuracao configuracao, BindingResult result) {

        configuracaoService.update(configuracao);
        return criarConfiguracao();
    }
}
