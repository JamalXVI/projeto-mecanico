package br.com.jamalxvi.controller;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.FornecedorDAO;
import br.com.jamalxvi.dao.PecasDAO;
import br.com.jamalxvi.modelo.Fornecedor;
import br.com.jamalxvi.modelo.Pecas;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class PecasController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private PecasDAO pecasDAO;
	private FornecedorDAO fornecedorDAO;
	
	@Inject
	public PecasController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, PecasDAO pecasDAO, FornecedorDAO fornecedorDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.pecasDAO = pecasDAO;
		this.fornecedorDAO = fornecedorDAO;
	}
	public PecasController() {
		this(null, null, null, null, null);
	}
	@Get("/Cadastro/Pecas/{id_f}")
	public void cadastrar_f(int id_f)
	{
		result.include("id_fornecedor", id_f);
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Pecas/")
	@NivelFuncionario(nivel=1)
	public void form()
	{
		Collection<Fornecedor> listar = fornecedorDAO.listar();
		result.include("fornecedores", listar);
	}
	@Post("/Cadastrar/Pecas/")
	@NivelFuncionario(nivel=1)
	public void cadastrar(@Valid Pecas peca, int id_fornecedor)
	{
		validator.onErrorRedirectTo(this).form();
		Fornecedor fornecedor = fornecedorDAO.pesquisar(id_fornecedor);
		peca.setFornecedor(fornecedor);
		pecasDAO.salvar(peca);
		result.redirectTo(this).finalizado(pecasDAO.ultimo());
	}

	@Get("/Json/Lista/Pecas/")
	public void lista()
	{
		Collection<Pecas> pecas = pecasDAO.listar();
		result.use(Results.json()).withoutRoot().from(pecas).include("fornecedor")
		.serialize();
	}
	@Get("/Editar/Pecas/{id}")
	@NivelFuncionario(nivel=1)
	public void editar(int id)
	{
		Pecas pecas = pecasDAO.pesquisar(id);
		Collection<Fornecedor> listar = fornecedorDAO.listar();
		result.include("fornecedores", listar);
		result.include("peca", pecas);
	}
	@Post("/Editado/Pecas/")
	@NivelFuncionario(nivel=1)
	public void editado(@Valid Pecas peca, int id_fornecedor)
	{
		validator.onErrorRedirectTo(this).form();
		Fornecedor fornecedor = fornecedorDAO.pesquisar(id_fornecedor);
		peca.setFornecedor(fornecedor);
		pecasDAO.atualizar(peca);
		result.redirectTo(this).finalizado(peca.getId());
	}
	@Post("/Deletar/Pecas")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		pecasDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Lista/Pecas")
	public void listar(String pesquisa)
	{
		result.include("pecas", pecasDAO.pesquisar_nome(pesquisa));
	}
	@Get("/Finalizado/Pecas/{id}")
	public void finalizado(int id)
	{
		result.include("peca", pecasDAO.pesquisar(id));
	}
	
}
