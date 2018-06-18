package br.com.jamalxvi.controller;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.addon.Endereco_Mec;
import br.com.jamalxvi.dao.ClienteDAO;
import br.com.jamalxvi.dao.ExecutadoDAO;
import br.com.jamalxvi.dao.FuncionarioDAO;
import br.com.jamalxvi.dao.MarcaDAO;
import br.com.jamalxvi.dao.OrcamentoDAO;
import br.com.jamalxvi.dao.PecasDAO;
import br.com.jamalxvi.dao.ServicoDAO;
import br.com.jamalxvi.dao.SolicitacaoDAO;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.modelo.Orcamento;
import br.com.jamalxvi.modelo.Pecas;
import br.com.jamalxvi.modelo.PecasUsadas;
import br.com.jamalxvi.modelo.Servicos;
import br.com.jamalxvi.modelo.ServicosFeitos;
import br.com.jamalxvi.modelo.Solicitacoes;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class OrcamentoController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private OrcamentoDAO orcamentoDAO;
	private MarcaDAO marcaDAO;
	private ClienteDAO clienteDAO;
	private ServicoDAO servicoDAO;
	private PecasDAO pecasDAO;
	private SolicitacaoDAO solicitacaoDAO;
	private FuncionarioDAO funcionarioDAO;
	private ExecutadoDAO executadoDAO;
	
	@Inject
	public OrcamentoController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, OrcamentoDAO orcamentoDAO,
			MarcaDAO marcaDAO, ClienteDAO clienteDAO, ServicoDAO servicoDAO,
			PecasDAO pecasDAO, SolicitacaoDAO solicitacaoDAO, FuncionarioDAO funcionarioDAO,
			ExecutadoDAO executadoDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.orcamentoDAO = orcamentoDAO;
		this.marcaDAO = marcaDAO;
		this.clienteDAO = clienteDAO;
		this.servicoDAO = servicoDAO;
		this.pecasDAO = pecasDAO;
		this.solicitacaoDAO = solicitacaoDAO;
		this.funcionarioDAO = funcionarioDAO;
		this.executadoDAO = executadoDAO;
	}
	public OrcamentoController() {
		this(null, null, null, null, null, null, null, null, null, null, null);
	}
	@Path("/Cadastro/Orcamento/{id}")
	public void cadastro_solicitacao(int id)
	{
		result.include("solicitacao", solicitacaoDAO.pesquisar(id));
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Orcamento/")
	@NivelFuncionario(nivel=2)
	public void form()
	{
		Collection<Marca> marcas = marcaDAO.listar();
		Collection<Cliente> clientes = clienteDAO.listar();
		result.include("marcas", marcas);
		result.include("clientes", clientes);
	}
	@Post("/Cadastrar/Orcamento/")
	@NivelFuncionario(nivel=2)
	public void cadastrar(@Valid Orcamento orcamento,
			@Valid List<PecasUsadas> produtos, List<ServicosFeitos> servicos, int id_solicitacao,
			boolean precisa_autorizar, String usuario, String senha)
	{
		validator.onErrorRedirectTo(this).form();
		if (precisa_autorizar) {
			if (!verificar_autorizacao(usuario, senha)) {
				result.redirectTo(ErrosController.class).erro_operacao();
				return;
			}
		} else if(usuario != null || senha != null){
			if(!usuario.isEmpty() || !senha.isEmpty()){
			if (!verificar_autorizacao(usuario, senha)) {
				result.redirectTo(ErrosController.class).erro_operacao();
				return;
			} }
		}
		Solicitacoes solicitacao = solicitacaoDAO.pesquisar(id_solicitacao);
		if (servicos != null) {
			if (servicos.size() > 0) {
				for (ServicosFeitos servico : servicos) {
					Servicos pesquisar = servicoDAO.pesquisar_normal(servico.getServico().getId());
					servico.setServico(pesquisar);
					pesquisar.getServicos_feitos().add(servico);
				}
			}
		}
		if (produtos != null) {
			if (produtos.size() > 0) {
				for (PecasUsadas produto : produtos) {
					Pecas pesquisar = pecasDAO.pesquisar_normal(produto.getPeca().getId());
					produto.setPeca(pesquisar);
					pesquisar.getPecas_usadas().add(produto);
				}
			}
		}
		orcamentoDAO.salvar(orcamento, produtos, servicos, solicitacao);
		result.redirectTo(this).finalizado(orcamentoDAO.ordem_desc());
	}	
	private boolean verificar_autorizacao(String usuario, String senha)
	{
		if (usuario == null || senha == null) {
			return false;
		}
		if (usuario.isEmpty() && senha.isEmpty()) {
			return false;
		}
		
		Collection<Funcionario> funcionarios = funcionarioDAO.logou(usuario, senha);
		if (funcionarios.size() > 0) {
			Funcionario funcionario = funcionarios.iterator().next();
			if (0 >= funcionario.getFuncao()) {
				return true;
			}
		}
		return false;
	}
	@Get("/Editar/Orcamento/{id}")
	@NivelFuncionario(nivel=2)
	public void editar(int id)
	{
		Orcamento orcamento = orcamentoDAO.pesquisa(id);
		if (!orcamento.isAtivo()) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		result.include("orcamento", orcamento);
	}
	@Post("/Editado/Orcamento/")
	@NivelFuncionario(nivel=2)
	public void editado(@Valid Orcamento orcamento,
			@Valid List<PecasUsadas> produtos, List<ServicosFeitos> servicos, int id_solicitacao,
			boolean precisa_autorizar, String usuario, String senha)
	{
		validator.onErrorRedirectTo(this).form();
		if (precisa_autorizar) {
			if (!verificar_autorizacao(usuario, senha)) {
				result.redirectTo(ErrosController.class).erro_operacao();
				return;
			}
		} else if(usuario != null || senha != null){
			if(!usuario.isEmpty() || !senha.isEmpty()){
			if (!verificar_autorizacao(usuario, senha)) {
				result.redirectTo(ErrosController.class).erro_operacao();
				return;
			} }
		}
		Orcamento orcamento_antigo = orcamentoDAO.pesquisa(orcamento.getId());
		if (orcamento_antigo.isAtendido()) {
			if (!orcamento_antigo.getPecas().isEmpty()) {
				for (PecasUsadas peca : orcamento_antigo.getPecas()) {
					pecasDAO.aumentar_estoque(peca.getPeca().getId(), peca.getQuantidade_usada());
				}
			}
		}
		Solicitacoes solicitacao = solicitacaoDAO.pesquisar(id_solicitacao);
		if (servicos != null) {
			if (servicos.size() > 0) {
				for (ServicosFeitos servico : servicos) {
					Servicos pesquisar = servicoDAO.pesquisar_normal(servico.getServico().getId());
					servico.setServico(pesquisar);
					pesquisar.getServicos_feitos().add(servico);
				}
			}
		}
		if (produtos != null) {
			if (produtos.size() > 0) {
				for (PecasUsadas produto : produtos) {
					Pecas pesquisar = pecasDAO.pesquisar_normal(produto.getPeca().getId());
					produto.setPeca(pesquisar);
					pesquisar.getPecas_usadas().add(produto);
				}
			}
		}
		orcamentoDAO.editar(orcamento, produtos, servicos, solicitacao);
		Orcamento orcamento_novo = orcamentoDAO.pesquisa(orcamento.getId());
		if (orcamento_novo.isAtendido()) {
			if (!orcamento_novo.getPecas().isEmpty()) {
				for (PecasUsadas peca : orcamento_novo.getPecas()) {
					pecasDAO.diminuir_estoque(peca.getPeca().getId(), peca.getQuantidade_usada());
				}
			}
		}
		result.redirectTo(this).finalizado(orcamento.getId());
	}	
	@Get("/Json/Lista/Orcamento/Ativos/")
	public void lista()
	{
		Collection<Orcamento> orcamentos = orcamentoDAO.lista();
		result.use(Results.json()).withoutRoot().from(orcamentos).include("solicitacao")
		.serialize();
	}
	@Get("/Json/Lista/Orcamento/Ativos/{id}")
	public void pegar_id(int id)
	{
		Orcamento orcamento = orcamentoDAO.pesquisa(id);
		result.use(Results.json()).withoutRoot().from(orcamento).include("solicitacao").include("pecas").include("servicos")
		.serialize();
	}
	@Get("/Imprimir/Orcamento/{id}")
	public void imprimir(int id)
	{
		Orcamento orcamento = orcamentoDAO.pesquisa(id);
		result.include("orcamento", orcamento);
		result.include("rua", Endereco_Mec.rua);
		result.include("num", Endereco_Mec.num);
		result.include("telefone", Endereco_Mec.telefone);
		result.include("cidade", Endereco_Mec.cidade);
	}
	@Get("/Visualizar/Orcamento/{id}")
	public void visualizar(int id)
	{
		Orcamento orcamento = null;
		try {
			orcamento = orcamentoDAO.pesquisa(id);
			if (!orcamento.isAtivo()) {
				result.redirectTo(ErrosController.class).erro_inativo();
			}
		} catch (Exception e) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		result.include("orcamento", orcamento);
	}
	@Get("/Visualizar/Orcamento/s/{id}")
	public void visualizar_s(int id)
	{
		result.redirectTo(this).visualizar(orcamentoDAO.pesquisa_solicitacao(id));
	}
	@Post("/Deletar/Orcamento")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		Orcamento orcamento = orcamentoDAO.deletar(id_esc_deletar);
		if (orcamento.isAtendido()) {
			int pesquisa_orcamento = executadoDAO.pesquisa_orcamento(id_esc_deletar);
			executadoDAO.deletar(pesquisa_orcamento);
		}
		result.redirectTo(ErrosController.class).removido();
	}
	
	@Get("/Finalizado/Orcamento/{id}")
	public void finalizado(int id)
	{
		result.include("orcamento", orcamentoDAO.pesquisa(id));
	}
	@Get("/Lista/Orcamento")
	public void listar(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		Collection<Orcamento> orcamentos = orcamentoDAO.pesquisar_nome(pesquisa);
		result.include("orcamentos", orcamentos);
	}
	@Get("/Lista/Orcamento/Id")
	public void listar_id(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		Collection<Orcamento> orcamentos = orcamentoDAO.pesquisar_id(pesquisa);
		result.include("orcamentos", orcamentos);
	}
}
