package br.com.jamalxvi.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.com.jamalxvi.modelo.Contas;
import br.com.jamalxvi.modelo.Pecas;
import br.com.jamalxvi.modelo.Produtor;
@RequestScoped
public class ContasDAO {
	
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.ContasDAO contasDAO;
	public ContasDAO()
	{
		this(null, null);
	}
	@Inject
	public ContasDAO(EntityManager manager, br.com.jamalxvi.manual_dao.ContasDAO contasDAO){
		this.manager = manager;
		this.contasDAO = contasDAO;
	}
	public void salvar(Contas conta, Pecas peca, Produtor produtor)
	{
		manager.getTransaction().begin();
		if (conta.isProduto()) {
			if (peca != null) {
				conta.setPeca(peca);
			}
		}
		if (conta.isTem_produtor()) {
			if (produtor != null) {
				conta.setProdutor(produtor);
			}
		}
		conta.setAtivo(true);
		manager.merge(conta);
		manager.getTransaction().commit();
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		Contas contas = manager.find(Contas.class, id);
		contas.setAtivo(false);
		manager.getTransaction().commit();
	}
	public Collection<Contas> lista()
	{
		return contasDAO.lista();
	}
	public Collection<Contas> pesquisar_data(int mes, int ano)
	{
		return contasDAO.pesquisa_mes(mes, ano);
	}
	public BigDecimal soma_mes(int mes, int ano)
	{
		return contasDAO.soma_mes(mes, ano);
	}
	public int ultimo()
	{
		return contasDAO.ultimo().getId();
	}
	public Contas pesquisar(int id)
	{
		return contasDAO.conta(id);
	}
	public Contas pagar(int id)
	{
		manager.getTransaction().begin();
		Contas contas = manager.find(Contas.class, id);
		contas.setPaga(true);
		manager.getTransaction().commit();
		return contas;
	}
	public Contas pagar_completo(Contas conta, String observacao, Calendar calendar)
	{
		manager.getTransaction().begin();
		conta.setDia_pago(calendar);
		conta.setObservacao(observacao);
		conta.setPaga(true);
		manager.merge(conta);
		manager.getTransaction().commit();
		return conta;
	}
	public Collection<Contas> pesquisar_dias(String inicio, String fim) {
		if (inicio == null || fim == null) {
			return lista();
		}
		if (inicio.equals("") || fim.equals("")) {
			return lista();
		}
		return contasDAO.pesquisa_dia(inicio, fim);
	}
}