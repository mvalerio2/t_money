package com.simulator.tmoney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CotacaoMb {

    /*
    Metodo cria a ligação com a view
    */
    @RequestMapping(value = {"/","/"}, method = RequestMethod.GET)
    public ModelAndView cotacao() {
         ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cotacao");
        return modelAndView;
    }
    
}
