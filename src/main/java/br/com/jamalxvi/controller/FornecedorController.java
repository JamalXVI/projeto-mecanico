package br.com.jamalxvi.controller;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.EstadoDAO;
import br.com.jamalxvi.dao.FornecedorDAO;
import br.com.jamalxvi.modelo.Cidade;
import br.com.jamalxvi.modelo.Estado;
import br.com.jamalxvi.modelo.Fornecedor;
import br.com.jamalxvi.modelo.TelefoneFornecedor;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class FornecedorController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private EstadoDAO estadodao;
	private FornecedorDAO fornecedorDAO;
	
	@Inject
	public FornecedorController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, EstadoDAO estadodao, FornecedorDAO fornecedorDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.estadodao = estadodao;
		this.fornecedorDAO = fornecedorDAO;
	}
	public FornecedorController() {
		this(null, null, null, null, null);
	}
   
	@Path("/Cadastro/Fabricante/")
	@NivelFuncionario(nivel=1)
	public void form()
	{
		Collection<Estado> estados = estadodao.listar();
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		result.include("estados", estados);
	}
	@Post("/Cadastrar/Fabricante/")
	@NivelFuncionario(nivel=1)
	public void cadastrar(@Valid Fornecedor fornecedor, @Valid List<TelefoneFornecedor> telefone)
	{
		validator.onErrorRedirectTo(this).form();
		fornecedorDAO.salvar(fornecedor, telefone);
		result.redirectTo(this).finalizado(fornecedorDAO.ultimo());
	}
	@Get("/Editar/Fabricante/{id}")
	@NivelFuncionario(nivel=1)
	public void editar(int id)
	{
		Fornecedor fornecedor = fornecedorDAO.pesquisar(id);
		Collection<Estado> estados = estadodao.listar();
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		result.include("estados", estados);
		result.include("fornecedor", fornecedor);
	}
	@Get("/Json/Fabricante/Telefones/{id}")
	public void lista_telefone(int id)
	{
		Fornecedor fornecedor = fornecedorDAO.pesquisar(id);
		List<TelefoneFornecedor> telefone = fornecedor.getTelefone();
		result.use(Results.json()).withoutRoot().from(telefone).include()
		.serialize();
	}
	@Post("/Editado/Fabricante/")
	@NivelFuncionario(nivel=1)
	public void editado(@Valid Fornecedor fornecedor,
			@Valid List<TelefoneFornecedor> telefone)
	{
		
		validator.onErrorRedirectTo(this).editar(fornecedor.getId());
		fornecedorDAO.apagar_telefones(fornecedor.getId());
		fornecedorDAO.atualizar(fornecedor, telefone);
		result.redirectTo(this).finalizado(fornecedor.getId());
		
	}
	@Get("/Lista/Fabricante")
    public void listar(String pesquisa)
    {
    	result.include("fornecedores", fornecedorDAO.pesquisar_nome(pesquisa));
    	result.include("pesquisa", pesquisa);
    }
	@Path("/Visualizar/Fornecedor/{id}")
	public void visualizar(int id)
	{
		Fornecedor fornecedor  = null;
		try {
			fornecedor = fornecedorDAO.pesquisar(id);
			if (!fornecedor.isAtivo()) {
				result.redirectTo(ErrosController.class).erro_inativo();
			}
		} catch (Exception e) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		Cidade cidade = estadodao.pesquisa_cidade(Integer.parseInt(fornecedor.getCidade()));
		result.include("fornecedor",fornecedor);
		result.include("cidade", cidade);
	}
	@Post("/Deletar/Fabricante")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		fornecedorDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Fabricante/{id}")
	public void finalizado(int id)
	{
		result.include("fornecedor", fornecedorDAO.pesquisar(id));
	}
}
