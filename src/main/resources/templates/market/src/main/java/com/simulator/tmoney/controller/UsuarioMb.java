package com.simulator.tmoney.controller;

import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.HistoricoTransacao;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.CarteiraService;
import com.simulator.tmoney.service.HistoricoTransacaoService;
import com.simulator.tmoney.service.UsuarioService;
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
public class UsuarioMb {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private HistoricoTransacaoService historicoTransacaoService;

    /*
    Metodo cria a ligação com a view e manda os objetos:
     - Usuario
     - Role do usuario logado
    */
    @RequestMapping(value = "/user/editarUsuario", method = RequestMethod.GET)
    public ModelAndView editarUsuario() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("user", usuario);
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("user/editarUsuario");
        return modelAndView;
    }

    /*
    Metodo recebe o objeto usuario e salva as alterações feitas
     */
    @PostMapping("/user/editarUsuario/save")
    public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
        Usuario userOld = usuarioService.findOne(usuario.getId());
        usuario.setRoles(userOld.getRoles());
        usuario.setCarteiras(userOld.getCarteiras());

        usuarioService.update(usuario);

        return editarUsuario();
    }

    /*
    Metodo cria a ligação com a view e manda os objetos:
     - Lista de carteiras do usuario
     - Role do usuario logado
    */
    @RequestMapping(value = "/user/listarCarteiras", method = RequestMethod.GET)
    public ModelAndView listarCarteiras() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("carteiras", carteiraService.findByUsuario(usuario));
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("user/listarCarteiras");
        return modelAndView;
    }

    /*
    Metodo recebe o id da carteira e se não tiver nenhum historico de transação ele deleta a carteira
     */
    @GetMapping("/user/carteira/deletar/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {

        List<HistoricoTransacao> list = historicoTransacaoService.findByCarteiraId(carteiraService.findOne(id));
        if(list.isEmpty()){
                carteiraService.delete(id);
        }
        return listarCarteiras();
    }


    /*
    Metodo cria a ligação com a view, recebe o objeto carteira e manda os objetos:
     - Carteira
     - Lista de historico de transações da carteira
     - Usuario
     - Role do usuario logado
    */
    @RequestMapping(value = "/user/historicoTransacoesUsuario", method = RequestMethod.GET)
    public ModelAndView verHistoricoTransacoes(Carteira carteira) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<HistoricoTransacao> historico = historicoTransacaoService.findByCarteiraId(carteira);
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("carteira", carteira);
        modelAndView.addObject("historico", historico);
        modelAndView.addObject("user", usuario);
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("user/historicoTransacoesUsuario");
        return modelAndView;
    }

    /*
    Metodo recebe o id da carteira, carrega ela e manda para o metodo verHistoricoTransacoes
     */
    @GetMapping("/user/carteira/historico/{id}")
    public ModelAndView view(@PathVariable("id") Integer id) {

        return verHistoricoTransacoes(carteiraService.findOne(id));
    }

    /*
    Metodo que chama listarCarteiras para voltar a tela que lista as carteiras do usuario
     */
    @RequestMapping(value = "/user/voltarCarteiras", method = RequestMethod.GET)
    public ModelAndView voltarLista() {

        return listarCarteiras();
    }
}
