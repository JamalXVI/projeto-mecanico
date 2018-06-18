package br.com.jamalxvi.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Pendura;
import br.com.jamalxvi.modelo.ServicosExecutados;
@RequestScoped
public class PenduraDAO {
	
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.PenduraDAO penduraDAO;
	public PenduraDAO()
	{
		this(null, null);
	}
	@Inject
	public PenduraDAO(EntityManager manager, br.com.jamalxvi.manual_dao.PenduraDAO penduraDAO){
		this.manager = manager;
		this.penduraDAO = penduraDAO;
	}
	public void salvar(Pendura pendura, Cliente cliente, boolean execucao, ServicosExecutados executados)
	{
		manager.getTransaction().begin();
		pendura.setCliente(cliente);
		pendura.setAtivo(true);
		pendura.setExecucao(execucao);
		if (execucao) {
			pendura.setServicosExecutados(executados);
		}
		manager.merge(pendura);
		manager.getTransaction().commit();
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		Pendura pendura = manager.find(Pendura.class, id);
		pendura.setAtivo(false);
		manager.getTransaction().commit();
	}
	public Collection<Pendura> lista()
	{
		return penduraDAO.lista();
	}
	public Collection<Pendura> pesquisar_data(int mes, int ano)
	{
		return penduraDAO.pesquisa_mes(mes, ano);
	}
	public Collection<Pendura> pesquisar_dias(String inicio, String fim)
	{
		if (inicio == null || fim == null) {
			return lista();
		}
		if (inicio.equals("") || fim.equals("")) {
			return lista();
		}
		return penduraDAO.pesquisa_dia(inicio, fim);
	}
	public Collection<Pendura> pesquisar_dias_nf(String inicio, String fim)
	{
		if (inicio == null || fim == null) {
			return lista();
		}
		if (inicio.equals("") || fim.equals("")) {
			return lista();
		}
		return penduraDAO.pesquisa_dia_nf(inicio, fim);
	}
	public Collection<Pendura> pesquisar_data_nf(int mes, int ano)
	{
		return penduraDAO.pesquisa_mes_nf(mes, ano);
	}
	public BigDecimal soma_pago(int mes, int ano)
	{
		return penduraDAO.soma_mes(mes, ano);
	}
	public BigDecimal soma_nao_pago(int mes, int ano)
	{
		return penduraDAO.soma_mes_nao_pago(mes, ano);
	}
	public int ultimo()
	{
		return penduraDAO.ultimo();
	}
	public Pendura pesquisar(int id)
	{
		return penduraDAO.pendura(id);
	}
	public Collection<Pendura> pesquisar_cliente(int id)
	{
		return penduraDAO.pesquisa_cliente(id);
	}
	public Collection<Pendura> pesquisar_execucao(int id)
	{
		return penduraDAO.pesquisa_execucao(id);
	}
	public Pendura pagar(int id)
	{
		manager.getTransaction().begin();
		Pendura pendura = manager.find(Pendura.class, id);
		if (pendura.isAtivo()) {
			pendura.setPago(true);
		}
		manager.getTransaction().commit();
		return pendura;
	}
	public Pendura pagar_completo(Pendura pendura, String observacao, Calendar calendar)
	{
		
		manager.getTransaction().begin();
		pendura.setDia_pago(calendar);
		pendura.setObservacao(observacao);
		pendura.setPago(true);
		manager.merge(pendura);
		manager.getTransaction().commit();
		return pendura;
	}
	public void pagar_executado(int id)
	{
		penduraDAO.pagar_executado(id);
	}
}