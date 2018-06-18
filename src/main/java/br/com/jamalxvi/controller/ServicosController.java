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
import br.com.jamalxvi.dao.ServicoDAO;
import br.com.jamalxvi.modelo.Servicos;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class ServicosController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private ServicoDAO servicoDAO;
	
	@Inject
	public ServicosController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, ServicoDAO servicoDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.servicoDAO = servicoDAO;
	}
	public ServicosController() {
		this(null, null, null, null);
	}
	@Path("/Cadastro/Servicos/")
	@NivelFuncionario(nivel=1)
	public void form()
	{
	}
	@Post("/Cadastrar/Servicos/")
	@NivelFuncionario(nivel=1)
	public void cadastrar(@Valid Servicos servico)
	{
		validator.onErrorRedirectTo(this).form();
		servicoDAO.salvar(servico);
		result.redirectTo(this).finalizado(servicoDAO.ultimo());
	}
	@Get("/Json/Lista/Servico/")
	public void lista()
	{
		Collection<Servicos> servicos = servicoDAO.listar();
		result.use(Results.json()).withoutRoot().from(servicos)
		.serialize();
	}
	@Get("/Lista/Servicos")
	public void listar(String pesquisa)
	{
		result.include("servicos", servicoDAO.pesquisar_nome(pesquisa));
	}
	@Post("/Deletar/Servicos")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		servicoDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Servico/{id}")
	public void finalizado(int id)
	{
		result.include("servico", servicoDAO.pesquisar(id));
	}
}
