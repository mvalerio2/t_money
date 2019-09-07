package com.simulator.tmoney.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simulator.tmoney.dto.Ordem;
import com.simulator.tmoney.model.Carteira;
import com.simulator.tmoney.model.HistoricoTransacao;
import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.CarteiraService;
import com.simulator.tmoney.service.ConfiguracaoService;
import com.simulator.tmoney.service.CriptomoedaService;
import com.simulator.tmoney.service.HistoricoTransacaoService;
import com.simulator.tmoney.service.TipoTransacaoService;
import com.simulator.tmoney.service.UsuarioService;

@Controller
public class MercadoMb {

    private static final String COMPRA = "Compra";
	private static final String VENDA = "Venda";

	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CriptomoedaService criptomoedaService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @Autowired
    private HistoricoTransacaoService historicoTransacaoService;

	/*
    Metodo cria a ligação com a view e manda os objetos:
     - Usuario
     - Role do usuario logado
     - Ordem de compra
    */
    @RequestMapping(value = "/market/comprar", method = RequestMethod.GET)
    public ModelAndView comprar() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("carteiras", usuario.getCarteiras());
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        
		Ordem ordem = new Ordem();
		modelAndView.addObject("ordem", ordem);

        modelAndView.setViewName("market/comprar");
        return modelAndView;
    }
    
    /*
    Metodo recebe o objeto usuario e os dados da compra para efetuar a transação
     */
    @PostMapping("/market/comprar/save")
    public ModelAndView executarCompra(@Valid Ordem ordem, BindingResult result) {
        Optional<Carteira> carteira = carteiraService.findOne(ordem.getCarteiraId());
        
        if (ordem.getTotal() > carteira.get().getSaldo()) {
        	FieldError error = new FieldError("", "total", "Total superior ao saldo disponível em carteira.");
        	result.addError(error);
        } else if (ordem.getQuantidade() <= 0) {
        	FieldError error = new FieldError("Campo Quantidade", "quantidade", "Valor inválido");
        	result.addError(error);
        } else {
	        Double quantidadeAtualCriptomoeda = ordem.getQuantidade();
	        Double valorAtualCarteira = carteira.get().getSaldo() - ordem.getTotal();
	        
	        HistoricoTransacao historicoTransacao = criarHistoricoTransacao(carteira.get(), quantidadeAtualCriptomoeda, valorAtualCarteira, COMPRA);
	        
	        carteira.get().setSaldo(valorAtualCarteira);
	        carteira.get().setSaldoCriptomoeda(quantidadeAtualCriptomoeda);
	        
	        carteiraService.update(carteira.get());

			historicoTransacaoService.save(historicoTransacao);
        }
        return comprar().addAllObjects(result.getModel());
    }
    
    /*
    Metodo cria a ligação com a view e manda os objetos:
     - Usuario
     - Role do usuario logado
     - Ordem de venda
    */
    @RequestMapping(value = "/market/vender", method = RequestMethod.GET)
    public ModelAndView vender() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("carteiras", usuario.getCarteiras());
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        
		Ordem ordem = new Ordem();
		modelAndView.addObject("ordem", ordem);

        modelAndView.setViewName("market/vender");
        return modelAndView;
    }
    
    /*
    Metodo recebe o objeto usuario e os dados da venda para efetuar a transação
     */
    @PostMapping("/market/vender/save")
    public ModelAndView executarVenda(@Valid Ordem ordem, BindingResult result) {
        Optional<Carteira> carteira = carteiraService.findOne(ordem.getCarteiraId());
        
        if (ordem.getQuantidade() > carteira.get().getSaldoCriptomoeda()) {
        	FieldError error = new FieldError("", "total", "Quantidade superior ao saldo de criptomoeda disponível em carteira.");
        	result.addError(error);
        } else if (ordem.getQuantidade() <= 0) {
        	FieldError error = new FieldError("", "quantidade", "Valor inválido");
        	result.addError(error);
        } else {
	        Double quantidadeAtualCriptomoeda = carteira.get().getSaldoCriptomoeda() - ordem.getQuantidade();
	        Double valorAtualCarteira = carteira.get().getSaldo() + ordem.getTotal();
	        
	        HistoricoTransacao historicoTransacao = criarHistoricoTransacao(carteira.get(), quantidadeAtualCriptomoeda, valorAtualCarteira, VENDA);
	        
	        carteira.get().setSaldo(valorAtualCarteira);
	        carteira.get().setSaldoCriptomoeda(quantidadeAtualCriptomoeda);
	        
	        carteiraService.update(carteira.get());
	        
			historicoTransacaoService.save(historicoTransacao);
        }
        return comprar().addAllObjects(result.getModel());
    }

	private HistoricoTransacao criarHistoricoTransacao(Carteira carteira, Double quantidadeAtualCriptomoeda,
			Double valorAtualCarteira, String tipoTransacao) {
        HistoricoTransacao historicoTransacao = new HistoricoTransacao();
		
		historicoTransacao.setCarteiraId(carteira);
		historicoTransacao.setDataHora(new Date());
		historicoTransacao.setQuantidadeAnteriorCriptomoeda(carteira.getSaldoCriptomoeda());
		historicoTransacao.setQuantidadeAtualCriptomoeda(quantidadeAtualCriptomoeda);
		historicoTransacao.setValorAnteriorCarteira(carteira.getSaldo());
		historicoTransacao.setValorAtualCarteira(valorAtualCarteira);
		historicoTransacao.setTipoTransacao(tipoTransacaoService.findByTipo(tipoTransacao));
		
		return historicoTransacao;
	}
}
