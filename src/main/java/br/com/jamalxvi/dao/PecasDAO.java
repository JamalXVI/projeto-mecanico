package br.com.jamalxvi.dao;


import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;
import br.com.jamalxvi.modelo.Pecas;
@RequestScoped
public class PecasDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.PecasDAO pecasDAO;
	public PecasDAO(){
		this(null, null);
	}
	@Inject
	public PecasDAO(EntityManager manager, br.com.jamalxvi.manual_dao.PecasDAO pecasDAO){
		this.manager = manager;
		this.pecasDAO = pecasDAO;
	}
	public void salvar(Pecas peca){
		manager.getTransaction().begin();
		peca.setAtivo(true);
		manager.persist(peca);
		manager.flush();
		manager.getTransaction().commit();manager.clear();
	}
	public void atualizar(Pecas peca){

		manager.getTransaction().begin();
		peca.setAtivo(true);
		manager.merge(peca);
		manager.getTransaction().commit();manager.clear();
	}
	public Collection<Pecas> listar()
	{
		 Collection<Pecas> list = pecasDAO.lista();
		return list;
	}
	public Pecas pesquisar(int id)
	{
		Pecas peca = pecasDAO.peca(id);
		return peca;
	}
	public Pecas pesquisar_normal(int id)
	{
		Pecas find = manager.find(Pecas.class, id);
		return find;
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Pecas> query = manager.createQuery("select p FROM Pecas p where p.id = :pId",
				Pecas.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Pecas peca = (Pecas)query.getResultList().get(0);
		peca.setAtivo(false);
		manager.getTransaction().commit();manager.clear();
	}
	public Collection<Pecas> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		List<Pecas> nome = pecasDAO.peca_nome(pesquisa);
		return nome;
	}
	public int ultimo()
	{
		Pecas peca = pecasDAO.ultimo();
		return peca.getId();
	}
	public void diminuir_estoque(int id, int qtd)
	{
		manager.getTransaction().begin();
		Pecas pecas = manager.find(Pecas.class, id);
		pecas.setEstoque(pecas.getEstoque() - qtd);
		manager.getTransaction().commit();
	}
	public void aumentar_estoque(int id, int qtd)
	{
		manager.getTransaction().begin();
		Pecas pecas = manager.find(Pecas.class, id);
		pecas.setEstoque(pecas.getEstoque() + qtd);
		manager.getTransaction().commit();
	}
}
