package com.simulator.tmoney.controller;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.Configuracao;
import com.simulator.tmoney.model.HistoricoTransacao;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.CarteiraService;
import com.simulator.tmoney.service.ConfiguracaoService;
import com.simulator.tmoney.service.CriptomoedaService;
import com.simulator.tmoney.service.HistoricoTransacaoService;
import com.simulator.tmoney.service.TipoTransacaoService;
import com.simulator.tmoney.service.UsuarioService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarteiraMb {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CriptomoedaService criptomoedaService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @Autowired
    private HistoricoTransacaoService historicoTransacaoService;


    @RequestMapping(value = "/user/criarCarteira", method = RequestMethod.GET)
    public ModelAndView criarCarteira() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("criptomoedas", criptomoedaService.findAll());
        modelAndView.addObject("carteira", new Carteira());
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("user/criarCarteira");
        return modelAndView;
    }

    @PostMapping("/user/criarCarteira/save")
    public ModelAndView save(@Valid Carteira carteira, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        carteira.setAtivo(Boolean.TRUE);
        List<Configuracao> list = configuracaoService.findAll();
        if (list.isEmpty()) {
            carteira.setSaldo(100.0);
        } else {
            carteira.setSaldo(list.iterator().next().getValorInicial());
        }
        carteira.setUsuarioId(usuario);
        carteiraService.save(carteira);

        return criarCarteira();
    }

    @RequestMapping(value = "/admin/carteiras", method = RequestMethod.GET)
    public ModelAndView carteiras() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("carteiras", carteiraService.findAll());
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("admin/carteiras");
        return modelAndView;
    }

    @GetMapping("/admin/carteira/inativar/{id}")
    public ModelAndView inativar(@PathVariable("id") Integer id) {

        Carteira carteira = carteiraService.findOne(id);
        carteira.setAtivo(Boolean.FALSE);
        carteiraService.update(carteira);

        return view(carteira.getUsuarioId().getId());
    }

    @GetMapping("/admin/carteira/ativar/{id}")
    public ModelAndView ativar(@PathVariable("id") Integer id) {

        Carteira carteira = carteiraService.findOne(id);
        carteira.setAtivo(Boolean.TRUE);
        carteiraService.update(carteira);

        return view(carteira.getUsuarioId().getId());
    }

    @GetMapping("/admin/carteira/reiniciar/{id}")
    public ModelAndView reiniciar(@PathVariable("id") Integer id) {
        Carteira carteira = carteiraService.findOne(id);
        
        HistoricoTransacao ht = new HistoricoTransacao();
        if(carteira.getSaldo() == null){
            ht.setValorAnteriorCarteira(0.0);
        }else{
            ht.setValorAnteriorCarteira(carteira.getSaldo());
        }
        if(carteira.getSaldo() == null){
            ht.setQuantidadeAnteriorCriptomoeda(0.0);
        }else{
            ht.setQuantidadeAnteriorCriptomoeda(carteira.getSaldoCriptomoeda());
        }

        List<Configuracao> list = configuracaoService.findAll();
        if (list.isEmpty()) {
            carteira.setSaldo(100.0);
        } else {
            carteira.setSaldo(list.iterator().next().getValorInicial());
        }
        carteira.setSaldoCriptomoeda(0.0);
        carteiraService.update(carteira);
        
        ht.setCarteiraId(carteira);
        ht.setDataHora(new Date());
        ht.setTipoTransacao(tipoTransacaoService.findByTipo("Reinicio de Saldo"));
        ht.setValorAtualCarteira(carteira.getSaldo());
        ht.setQuantidadeAtualCriptomoeda(carteira.getSaldoCriptomoeda());
        historicoTransacaoService.save(ht);

        return view(carteira.getUsuarioId().getId());
    }
    
    
    @RequestMapping(value = "/admin/voltarLista", method = RequestMethod.GET)
    public ModelAndView voltarLista() {
        
        return carteiras();
    }
    
    @RequestMapping(value = "/admin/usuario", method = RequestMethod.GET)
    public ModelAndView verUsuario(Carteira carteira) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" Carregando tela");
        System.out.println(carteira.getId().toString());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("user", usuario);
        modelAndView.addObject("carteira", carteira);
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("/admin/usuario");
        return modelAndView;
    }

    @GetMapping("/admin/usuario/{id}")
    public ModelAndView view(@PathVariable("id") Integer id) {
        Carteira c = carteiraService.findOne(id);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" Enviando a carteira");
        System.out.println(c.getId().toString());
        return verUsuario(c);
    }
}
