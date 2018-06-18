package br.com.jamalxvi.dao;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.modelo.Cidade;
import br.com.jamalxvi.modelo.Estado;
@RequestScoped
public class EstadoDAO {
	private EntityManager manager;
	public EstadoDAO(){
		this(null);
	}
	@Inject
	public EstadoDAO(EntityManager manager){
		this.manager = manager;
	}
	public Collection<Estado> listar()
	{
		TypedQuery<Estado> query = manager.createQuery("Select e from Estado e",
				Estado.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		Collection<Estado> estados = query.getResultList();
		
		return estados;
	}
	public Cidade pesquisa_cidade(int id)
	{
		TypedQuery<Cidade> query = manager.createQuery("Select c from Cidade c where c.id = :pId",
				Cidade.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Cidade cidade = query.getResultList().get(0);
		return cidade;
	}
}
