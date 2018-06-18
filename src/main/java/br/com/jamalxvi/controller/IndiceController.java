package br.com.jamalxvi.controller;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jamalxvi.dao.FuncionarioDAO;
import br.com.jamalxvi.dao.OrcamentoDAO;
import br.com.jamalxvi.dao.SolicitacaoDAO;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.Orcamento;
import br.com.jamalxvi.modelo.Solicitacoes;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class IndiceController {
	
	private UsuarioLogado usuarioLogado;
	private Result result;
	@SuppressWarnings("unused")
	private Validator validator;
	private FuncionarioDAO funcionarioDAO;
	private SolicitacaoDAO solicitacoesDAO;
	private OrcamentoDAO orcamentoDAO;

	@Inject
	public IndiceController(UsuarioLogado usuarioLogado, Result result, Validator validator, FuncionarioDAO funcionarioDAO,
			SolicitacaoDAO solicitacoesDAO, OrcamentoDAO orcamentoDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.funcionarioDAO = funcionarioDAO;
		this.solicitacoesDAO = solicitacoesDAO;
		this.orcamentoDAO = orcamentoDAO;
	}
	public IndiceController() {
		this(null, null, null, null, null, null);
	}
	@Path("/")
	public void inicio()
	{
		Funcionario funcionario = funcionarioDAO.pesquisar_id(usuarioLogado.getUsuario().getId());
		Collection<Solicitacoes> solicitacoes = solicitacoesDAO.listar_ativas();
		Collection<Orcamento> orcamentos = orcamentoDAO.lista();
		result.include("funcionario", funcionario);
		result.include("solicitacoes", solicitacoes);
		result.include("orcamentos", orcamentos);
	}
}
