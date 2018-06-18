package br.com.jamalxvi.dao;

import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Fotos_Solicitacoes;
import br.com.jamalxvi.modelo.IdentificacaoVeiculo;
import br.com.jamalxvi.modelo.Modelo;

@RequestScoped
public class VeiculoDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.VeiculoDAO veiculoDAO;
	public VeiculoDAO(){
		this(null, null);
	}
	@Inject
	public VeiculoDAO(EntityManager manager, br.com.jamalxvi.manual_dao.VeiculoDAO veiculoDAO){
		this.manager = manager;
		this.veiculoDAO = veiculoDAO;
	}
	public void salvar(IdentificacaoVeiculo veiculo, Cliente cliente, Modelo modelo)
	{
		manager.getTransaction().begin();
		veiculo.setModelo(modelo);
		modelo.getVeiculos().add(veiculo);
		cliente.getVeiculos().add(veiculo);
		veiculo.setCliente(cliente);
		veiculo.setAtivo(true);
		cliente.setAtivo(true);
		manager.persist(veiculo);
		manager.flush();
		manager.getTransaction().commit();manager.clear();
		
	}
	public Collection<IdentificacaoVeiculo> listar()
	{
		return veiculoDAO.veiculos();
	}
	public IdentificacaoVeiculo pegar_ultimo()
	{
		return veiculoDAO.ultimo();
	}
	public IdentificacaoVeiculo pesquisar(int id)
	{
		return  veiculoDAO.veiculo(id);
	}
	public Collection<IdentificacaoVeiculo> pesquisar_placa(String placa)
	{
		if (placa == null) {
			placa = "";
		}
		return  veiculoDAO.pesquisa(placa);
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<IdentificacaoVeiculo> query = manager.createQuery("Select v From IdentificacaoVeiculo v where v.id = :pId",
				IdentificacaoVeiculo.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		IdentificacaoVeiculo veiculo = query.getResultList().get(0);
		veiculo.setAtivo(false);
		
		manager.getTransaction().commit();manager.clear();
	}
	public Fotos_Solicitacoes pesquisar_imagem(int id)
	{
		Fotos_Solicitacoes solicitacoes = veiculoDAO.foto_solicitacao(id);
		return solicitacoes;
	}
}
