package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Transactional
@Repository
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto){
		manager.persist(produto);
	}

	public List<Produto> findAll() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto findId(Long id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos preco where "
				+ "p.id = :id", Produto.class).setParameter("id", id).getSingleResult();	
	}

}
