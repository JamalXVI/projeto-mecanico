package br.com.jamalxvi.dao;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.modelo.Fornecedor;
import br.com.jamalxvi.modelo.Pecas;
import br.com.jamalxvi.modelo.TelefoneFornecedor;

@RequestScoped
public class FornecedorDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.FornecedorDAO fornecedorDAO;
	public FornecedorDAO(){
		this(null, null);
	}
	@Inject
	public FornecedorDAO(EntityManager manager, br.com.jamalxvi.manual_dao.FornecedorDAO fornecedorDAO){
		this.manager = manager;
		this.fornecedorDAO = fornecedorDAO;
	}
	public void salvar(Fornecedor fornecedor, List<TelefoneFornecedor> telefone){
		manager.getTransaction().begin();
		fornecedor.setAtivo(true);
		fornecedor.getIdentificacao().setFornecedor(fornecedor);
		manager.persist(fornecedor);
		if (telefone != null) {
			if (telefone.size() > 0) {
				for (TelefoneFornecedor telefoneFornecedor : telefone) {
					telefoneFornecedor.setAtivo(true);
					telefoneFornecedor.setFornecedor(fornecedor);
					manager.persist(telefoneFornecedor);
				}
			}
		}
		manager.flush();
		manager.getTransaction().commit();manager.clear();
	}
	public Fornecedor pesquisar(int id)
	{
		Fornecedor fornecedor = fornecedorDAO.fornecedor(id);
		return fornecedor;
	}
	public Collection<Fornecedor> pesquisar_nome(String nome)
	{
		if (nome == null) {
			nome = "";
		}
		Collection<Fornecedor> f = fornecedorDAO.fornecedor_nome(nome);
		return f;
	}
	public void apagar_telefones(int id)
	{
		Fornecedor fornecedor = pesquisar(id);
		
		List<TelefoneFornecedor> telefoneFornecedor = fornecedor.getTelefone();
		manager.getTransaction().begin();
			for (TelefoneFornecedor tel : telefoneFornecedor) {
				tel.setAtivo(false);
				manager.merge(tel);
			}
		manager.getTransaction().commit();manager.clear();
		
	}
	public Collection<Fornecedor> listar() {
		Collection<Fornecedor> f = fornecedorDAO.fornecedores();
		return f;
	}
	public void atualizar(Fornecedor fornecedor, List<TelefoneFornecedor> telefone) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		fornecedor.setAtivo(true);
		fornecedor.getIdentificacao().setFornecedor(fornecedor);
		manager.merge(fornecedor);
		if (telefone != null) {
			if (telefone.size() > 0) {
				for (TelefoneFornecedor telefoneFornecedor : telefone) {
					telefoneFornecedor.setAtivo(true);
					telefoneFornecedor.setFornecedor(fornecedor);
					manager.merge(telefoneFornecedor);
				}
			}
		}
		manager.getTransaction().commit();manager.clear();
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Fornecedor> query = manager.createQuery("Select f from Fornecedor f where f.id = :pId",
				Fornecedor.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Fornecedor fornecedor = query.getResultList().get(0);
		for (Pecas p : fornecedor.getPecas()) {
			p.setAtivo(false);
		}
		fornecedor.setAtivo(false);
		manager.getTransaction().commit();manager.clear();
	}
	public int ultimo()
	{
		Fornecedor fornecedor = fornecedorDAO.ultimo();
		return fornecedor.getId();
	}
}
