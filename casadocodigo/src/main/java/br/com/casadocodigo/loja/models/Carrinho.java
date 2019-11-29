package br.com.casadocodigo.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Carrinho {
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void add(CarrinhoItem carrinhoItem) {
		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);
	}

	private int getQuantidade(CarrinhoItem carrinhoItem) {
		if (itens.containsKey(carrinhoItem)) {
			return itens.get(carrinhoItem);
		} else {
			return 0;
		}
	}
	
	public int getQuantidade() {
		return itens.values().stream().collect(Collectors.summingInt(Integer::intValue));
	}

}
