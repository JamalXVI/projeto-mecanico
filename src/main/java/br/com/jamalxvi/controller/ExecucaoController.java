package br.com.jamalxvi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.jamalxvi.addon.Endereco_Mec;
import br.com.jamalxvi.dao.ClienteDAO;
import br.com.jamalxvi.dao.ExecutadoDAO;
import br.com.jamalxvi.dao.FuncionarioDAO;
import br.com.jamalxvi.dao.MarcaDAO;
import br.com.jamalxvi.dao.OrcamentoDAO;
import br.com.jamalxvi.dao.PenduraDAO;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.modelo.Orcamento;
import br.com.jamalxvi.modelo.ServicosExecutados;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class ExecucaoController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private MarcaDAO marcaDAO;
	private ClienteDAO clienteDAO;
	private FuncionarioDAO funcionarioDAO;
	private OrcamentoDAO orcamentoDAO;
	private ExecutadoDAO executadoDAO;
	private PenduraDAO penduraDAO;
	
	@Inject
	public ExecucaoController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator,
			MarcaDAO marcaDAO, ClienteDAO clienteDAO, FuncionarioDAO funcionarioDAO, OrcamentoDAO orcamentoDAO,
			ExecutadoDAO executadoDAO, PenduraDAO penduraDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.marcaDAO = marcaDAO;
		this.clienteDAO = clienteDAO;
		this.funcionarioDAO = funcionarioDAO;
		this.orcamentoDAO = orcamentoDAO;
		this.executadoDAO = executadoDAO;
		this.penduraDAO = penduraDAO;
	}
	public ExecucaoController() {
		this(null, null, null, null, null, null, null, null, null);
	}
	@Path("/Cadastro/Executados/{id}")
	public void cadastro_orcamento(int id)
	{
		result.include("orcamento", orcamentoDAO.pesquisa(id));
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Executados/")
	public void form()
	{
		Collection<Marca> marcas = marcaDAO.listar();
		Collection<Cliente> clientes = clienteDAO.listar();
		Collection<Funcionario> funcionarios = funcionarioDAO.lista();
		result.include("marcas", marcas);
		result.include("clientes", clientes);
		result.include("funcionarios", funcionarios);
	}
	@Post("/Cadastrar/Executados/")
	public void cadastrar(@Valid ServicosExecutados executado,  @Valid @NotEmpty String horario, 
			@Valid @NotNull int id_funcionario, @Valid @NotNull int id_autorizador, @Valid @NotNull int id_orcamento)
	{
		System.out.println(executado.getForma_pagamento());
		validator.onErrorRedirectTo(this).form();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(horario));
		} catch (ParseException e) {
			result.redirectTo(this).form();
			return;
		}
		executado.setEntrega(calendar);
		Funcionario funcionario = funcionarioDAO.pesquisar_id_normal(id_funcionario);
		Cliente cliente = clienteDAO.pesquisar_id_normal(id_autorizador);
		Orcamento orcamento = orcamentoDAO.pesquisa(id_orcamento);
		executadoDAO.salvar(executado, orcamento, cliente, funcionario);
		result.redirectTo(this).finalizado(executadoDAO.descendente());
		
	}	
	@Get("/Imprimir/Executado/{id}")
	public void imprimir(int id)
	{
		ServicosExecutados executados = executadoDAO.pesquisa(id);
		result.include("executado", executados);
		result.include("rua", Endereco_Mec.rua);
		result.include("num", Endereco_Mec.num);
		result.include("telefone", Endereco_Mec.telefone);
		result.include("cidade", Endereco_Mec.cidade);
	}
	@Get("/Visualizar/Executado/{id}")
	public void visualizar(int id)
	{
		ServicosExecutados executados = null;
		try {
			executados = executadoDAO.pesquisa(id);
			if (!executados.isAtivo()) {
				result.redirectTo(ErrosController.class).erro_inativo();
			}
		} catch (Exception e) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		result.include("executado", executados);
	}
	@Get("/Visualizar/Executado/o/{id}")
	public void visualizar_o(int id)
	{
		result.redirectTo(this).visualizar(executadoDAO.pesquisa_orcamento(id));
	}
	@Post("/Deletar/Executado")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		executadoDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Executado/{id}")
	public void finalizado(int id)
	{
		result.include("executado", executadoDAO.pesquisa(id));
	}
	@Get("/Pagar/Executado/{id}")
	@NivelFuncionario(nivel=0)
	public void pagar(int id)
	{
		ServicosExecutados executados = executadoDAO.pesquisa(id);
		if (!executados.isAtivo()) {
			result.redirectTo(ErrosController.class).erro_inativo();
			return;
		}
		executadoDAO.pagar(id);
		penduraDAO.pagar_executado(id);
		result.include("executado", executados);
	}
}

