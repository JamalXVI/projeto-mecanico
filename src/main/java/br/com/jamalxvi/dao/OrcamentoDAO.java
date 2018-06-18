package br.com.jamalxvi.dao;


import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jamalxvi.modelo.Orcamento;
import br.com.jamalxvi.modelo.PecasUsadas;
import br.com.jamalxvi.modelo.ServicosFeitos;
import br.com.jamalxvi.modelo.Solicitacoes;
@RequestScoped
public class OrcamentoDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.OrcamentoDAO orcamentoDAO;
	public OrcamentoDAO(){
		this(null, null);
	}
	@Inject
	public OrcamentoDAO(EntityManager manager, br.com.jamalxvi.manual_dao.OrcamentoDAO orcamentoDAO){
		this.manager = manager;
		this.orcamentoDAO = orcamentoDAO;
	}
	public void salvar(Orcamento orcamento, List<PecasUsadas> produtos, List<ServicosFeitos> servicos, Solicitacoes solicitacao){
		manager.getTransaction().begin();
		solicitacao.setAtendida(true);
		orcamento.setAtivo(true);
		if (produtos != null) {

			orcamento.setPecas(produtos);
		}
		if (servicos != null) {
			orcamento.setServicos(servicos);
		}
		solicitacao.setOrcamento(orcamento);;
		orcamento.setSolicitacao(solicitacao);
		
		manager.merge(orcamento);
		manager.getTransaction().commit();
	}
	public void editar(Orcamento orcamento, List<PecasUsadas> produtos, List<ServicosFeitos> servicos, Solicitacoes solicitacao){
		manager.getTransaction().begin();
		solicitacao.setAtendida(true);
		orcamento.setAtivo(true);
		if (produtos != null) {

			orcamento.setPecas(produtos);
		}
		if (servicos != null) {
			orcamento.setServicos(servicos);
		}
		solicitacao.setOrcamento(orcamento);;
		orcamento.setSolicitacao(solicitacao);
		manager.merge(orcamento);
		manager.getTransaction().commit();
	}
	public Collection<Orcamento> lista()
	{
		Collection<Orcamento> ativos = orcamentoDAO.ativos();
		return ativos;
	}
	public Orcamento pesquisa(int id)
	{
		Orcamento orcamento = orcamentoDAO.orcamento(id);
		return orcamento;
	}
	public Orcamento deletar(int id)
	{
		manager.getTransaction().begin();
		Orcamento orcamento = manager.find(Orcamento.class, id);
		orcamento.setAtivo(false);
		orcamento.getSolicitacao().setAtivo(false);
		manager.getTransaction().commit();manager.clear();
		return orcamento;
	}
	public int pesquisa_solicitacao(int id)
	{
		return orcamentoDAO.solicitacar_orcamento(id);
		
	}
	public int ordem_desc()
	{
		Orcamento orcamento = orcamentoDAO.ultimo();
		return orcamento.getId();
	}
	public Collection<Orcamento> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		List<Orcamento> orcamento_nome = orcamentoDAO.orcamento_nome(pesquisa);
		return orcamento_nome;
	}
	public Collection<Orcamento> pesquisar_id(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		Collection<Orcamento> orcamento = orcamentoDAO.pesquisa_id(pesquisa);
		return orcamento;
	}

}
