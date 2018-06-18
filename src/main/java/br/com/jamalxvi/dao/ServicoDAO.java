package br.com.jamalxvi.dao;


import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.manual_dao.ServicosDAO;
import br.com.jamalxvi.modelo.Servicos;
@RequestScoped
public class ServicoDAO {
	private EntityManager manager;
	private ServicosDAO servicosDAO;
	public ServicoDAO(){
		this(null, null);
	}
	@Inject
	public ServicoDAO(EntityManager manager, ServicosDAO servicosDAO){
		this.manager = manager;
		this.servicosDAO = servicosDAO;
	}
	public void salvar(Servicos servico){
		manager.getTransaction().begin();
		servico.setAtivo(true);
		manager.persist(servico);
		manager.flush();
		manager.getTransaction().commit();manager.clear();
	}
	public Collection<Servicos> listar()
	{
		Collection<Servicos> lista = servicosDAO.lista();
		return lista;
	}
	
	public Servicos pesquisar(int id)
	{
		Servicos servico = servicosDAO.servico(id);
		return servico;
	}
	public Servicos pesquisar_normal(int id)
	{
		return manager.find(Servicos.class, 1);
	}
	public Collection<Servicos> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		List<Servicos> servico_nome = servicosDAO.servico_nome(pesquisa);
		return servico_nome;
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Servicos> query = manager.createQuery("select s from Servicos s where "
				+ "s.id = :pId",
				Servicos.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Servicos servico = query.getResultList().get(0);
		servico.setAtivo(false);
		manager.getTransaction().commit();manager.clear();
	}
	public int ultimo()
	{
		return servicosDAO.ultimo().getId();
	}

}
