package com.simulator.tmoney.controller;

import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuario = new Usuario();
        modelAndView.addObject("user", usuario);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Usuario usuario, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuarioExistente = usuarioService.findByEmail(usuario.getEmail());
        if (usuarioExistente != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            usuarioService.save(usuario);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Usuario());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("user", usuario);
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

}
