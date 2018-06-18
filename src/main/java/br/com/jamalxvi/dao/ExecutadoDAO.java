package br.com.jamalxvi.dao;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.infra.Soma_Dados_Contas_Mes;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.Orcamento;
import br.com.jamalxvi.modelo.Pecas;
import br.com.jamalxvi.modelo.PecasUsadas;
import br.com.jamalxvi.modelo.ServicosExecutados;
import br.com.jamalxvi.modelo.ServicosFeitos;

@RequestScoped
public class ExecutadoDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.ExecutadoDAO executadoDAO;
	public ExecutadoDAO(){
		this(null, null);
	}
	@Inject
	public ExecutadoDAO(EntityManager manager, br.com.jamalxvi.manual_dao.ExecutadoDAO executadoDAO){
		this.manager = manager;
		this.executadoDAO = executadoDAO;
	}
	public void salvar(ServicosExecutados executado, Orcamento orcamento, Cliente cliente, Funcionario funcionario){
		manager.getTransaction().begin();
		orcamento.setAtendido(true);
		executado.setAtivo(true);
		executado.setAutorizador(cliente);
		executado.setOrcamento(orcamento);
		executado.setMecanico(funcionario);
		funcionario.getExecutados().add(executado);
		cliente.getAutorizados().add(executado);
		orcamento.setExecutados(executado);;
		orcamento.getSolicitacao().setOrcamento(orcamento);
		for (ServicosFeitos s : orcamento.getServicos()) {
			s.setOrcamento(orcamento);
		}
		manager.persist(executado);
		manager.merge(orcamento);
		if (orcamento.getPecas() != null) {
			if (orcamento.getPecas().size() > 0) {
				for (PecasUsadas usada : orcamento.getPecas()) {
					Pecas pecas = manager.find(Pecas.class, usada.getPeca().getId());
					pecas.setEstoque(pecas.getEstoque() - usada.getQuantidade_usada());
				}
			}
			
		}
		manager.getTransaction().commit();
	}
	public ServicosExecutados pesquisa(int id)
	{
		ServicosExecutados executado = executadoDAO.executado(id);
		return executado;
	}
	public int descendente()
	{
		ServicosExecutados executado = executadoDAO.ultimo();
		return executado.getId();
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<ServicosExecutados> query = manager.createQuery("Select s from "
				+ "ServicosExecutados s where s.id = :pId",
				ServicosExecutados.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		ServicosExecutados executado = (ServicosExecutados)query.getResultList().get(0);
		executado.setAtivo(false);
		executado.getOrcamento().setAtivo(false);
		executado.getOrcamento().getSolicitacao().setAtivo(false);
		manager.getTransaction().commit();manager.clear();
		
	}
	public Soma_Dados_Contas_Mes soma_mes(int mes, int ano)
	{
		return executadoDAO.soma_mes(mes, ano);
	}
	public int pesquisa_orcamento(int id)
	{
		ServicosExecutados executado = executadoDAO.orcamento_id(id);
		return executado.getId();
	}
	public void pagar(int id)
	{
		executadoDAO.pagar(id);
	}
}
