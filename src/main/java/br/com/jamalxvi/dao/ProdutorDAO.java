package br.com.jamalxvi.dao;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.modelo.Produtor;
import br.com.jamalxvi.modelo.TelefoneProdutor;

@RequestScoped
public class ProdutorDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.ProdutorDAO produtorDAO;
	public ProdutorDAO(){
		this(null, null);
	}
	@Inject
	public ProdutorDAO(EntityManager manager, br.com.jamalxvi.manual_dao.ProdutorDAO produtorDAO){
		this.manager = manager;
		this.produtorDAO = produtorDAO;
	}
	public void salvar(Produtor produtor, List<TelefoneProdutor> telefone){
		manager.getTransaction().begin();
		produtor.setAtivo(true);
		produtor.getIdentificacao().setProdutor(produtor);
		manager.persist(produtor);
		if (telefone != null) {
			if (telefone.size() > 0) {
				for (TelefoneProdutor telefoneProdutor : telefone) {
					telefoneProdutor.setAtivo(true);
					telefoneProdutor.setProdutor(produtor);
					manager.persist(telefoneProdutor);
				}
			}
		}
		manager.getTransaction().commit();
	}
	
	public Produtor pesquisar(int id)
	{
		Produtor fornecedor = produtorDAO.fornecedor(id);
		return fornecedor;
	}
	public Collection<Produtor> pesquisar_nome(String nome)
	{
		if (nome == null) {
			nome = "";
		}
		Collection<Produtor> f = produtorDAO.fornecedor_nome(nome);
		return f;
	}
	public void apagar_telefones(int id)
	{
		Produtor produtor = pesquisar(id);
		
		List<TelefoneProdutor> telefones = produtor.getTelefone();
		manager.getTransaction().begin();
			for (TelefoneProdutor tel : telefones) {
				tel.setAtivo(false);
				manager.merge(tel);
			}
		manager.getTransaction().commit();manager.clear();
		
	}
	public Collection<Produtor> listar() {
		Collection<Produtor> f = produtorDAO.fornecedores();
		return f;
	}
	public void atualizar(Produtor produtor, List<TelefoneProdutor> telefone) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		produtor.setAtivo(true);
		produtor.getIdentificacao().setProdutor(produtor);
		manager.merge(produtor);
		if (telefone != null) {
			if (telefone.size() > 0) {
				for (TelefoneProdutor telefoneProdutor : telefone) {
					telefoneProdutor.setAtivo(true);
					telefoneProdutor.setProdutor(produtor);
					manager.merge(telefoneProdutor);
				}
			}
		}
		manager.getTransaction().commit();manager.clear();
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Produtor> query = manager.createQuery("Select f from Produtor f where f.id = :pId",
				Produtor.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Produtor produtor = query.getResultList().get(0);
		produtor.setAtivo(false);
		manager.getTransaction().commit();manager.clear();
	}
	public int ultimo()
	{
		Produtor produtor = produtorDAO.ultimo();
		return produtor.getId();
	}
}
