package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Carrinho;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private Carrinho carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.pegaPor(produtoId);
		
		CarrinhoItem carrinhoItem = criaCarrinhoItem(produto, tipoPreco);
		carrinho.add(carrinhoItem);
		
		return new ModelAndView("redirect:/carrinho");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView view = new ModelAndView("/carrinho/itens");
		
		return view;
	}
	
	@RequestMapping(value = "/remover", method = RequestMethod.POST)
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);
		
		return new ModelAndView("redirect:/carrinho");
	}

	private CarrinhoItem criaCarrinhoItem(Produto produto, TipoPreco tipoPreco) {
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		
		return carrinhoItem;
	}

}
