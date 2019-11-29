package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("SELECT p FROM produto p", Produto.class).getResultList();
	}

	public Produto pegaPor(int id) {
		TypedQuery<Produto> query = manager.createQuery("SELECT p FROM produto p JOIN FETCH p.precos preco WHERE p.id = :id", Produto.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}	

}
