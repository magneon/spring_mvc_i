package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.Carrinho;
import br.com.casadocodigo.loja.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes redirectAttributes) {
		return () -> {
			String pagamentoUri = "http://book-payment.herokuapp.com/payment";
			ModelAndView view;
			
			try {
				String response = restTemplate.postForObject(pagamentoUri, new DadosPagamento(carrinho.getTotal()), String.class);
				redirectAttributes.addFlashAttribute("sucesso", response);
				
				view = new ModelAndView("redirect:/produtos");			
			} catch (HttpClientErrorException httpClientErrorException) {
				redirectAttributes.addFlashAttribute("falha", httpClientErrorException.getMessage());
				
				view = new ModelAndView("redirect:/produtos");
			}
			
			return view;
		};
	}
	
}
