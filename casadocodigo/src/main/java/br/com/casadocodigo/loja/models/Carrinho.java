package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class Carrinho {
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void add(CarrinhoItem carrinhoItem) {
		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);
	}
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public Integer getQuantidade(CarrinhoItem carrinhoItem) {
		if (itens.containsKey(carrinhoItem)) {
			return itens.get(carrinhoItem);
		} else {
			return 0;
		}
	}
	
	public int getQuantidade() {
		return itens.values().stream().collect(Collectors.summingInt(Integer::intValue));
	}
	
	public BigDecimal getTotal(CarrinhoItem carrinhoItem) {
		return carrinhoItem.getTotal(getQuantidade(carrinhoItem));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem carrinhoItem : itens.keySet()) {
			total = total.add(getTotal(carrinhoItem));			
		}
		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		itens.remove(carrinhoItem);
	}

}