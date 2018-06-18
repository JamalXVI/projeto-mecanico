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
import br.com.jamalxvi.dao.ProdutorDAO;
import br.com.jamalxvi.modelo.Cidade;
import br.com.jamalxvi.modelo.Estado;
import br.com.jamalxvi.modelo.Produtor;
import br.com.jamalxvi.modelo.TelefoneProdutor;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class ProdutorController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private EstadoDAO estadodao;
	private ProdutorDAO produtorDAO;
	
	@Inject
	public ProdutorController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator,
			EstadoDAO estadodao, ProdutorDAO produtorDAO) {
		
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.estadodao = estadodao;
		this.produtorDAO = produtorDAO;
	}
	public ProdutorController() {
		this(null, null, null, null, null);
	}
   
	@Path("/Cadastro/Produtor/")
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
	@Post("/Cadastrar/Produtor/")
	@NivelFuncionario(nivel=1)
	public void cadastrar(@Valid Produtor produtor, @Valid List<TelefoneProdutor> telefone)
	{
		validator.onErrorRedirectTo(this).form();
		produtorDAO.salvar(produtor, telefone);
		result.redirectTo(this).finalizado(produtorDAO.ultimo());
	}
	@Get("/Editar/Produtor/{id}")
	@NivelFuncionario(nivel=1)
	public void editar(int id)
	{
		Produtor produtor = produtorDAO.pesquisar(id);
		Collection<Estado> estados = estadodao.listar();
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		result.include("estados", estados);
		result.include("produtor", produtor);
	}
	@Get("/Json/Produtor/Telefones/{id}")
	public void lista_telefone(int id)
	{
		Produtor produtor = produtorDAO.pesquisar(id);
		List<TelefoneProdutor> telefone = produtor.getTelefone();
		result.use(Results.json()).withoutRoot().from(telefone).include()
		.serialize();
	}
	@Post("/Editado/Produtor/")
	@NivelFuncionario(nivel=1)
	public void editado(@Valid Produtor produtor,
			@Valid List<TelefoneProdutor> telefone)
	{
		
		validator.onErrorRedirectTo(this).editar(produtor.getId());
		produtorDAO.apagar_telefones(produtor.getId());
		produtorDAO.atualizar(produtor, telefone);
		result.redirectTo(this).finalizado(produtor.getId());
		
	}
	@Get("/Lista/Produtor")
    public void listar(String pesquisa)
    {
    	result.include("produtores", produtorDAO.pesquisar_nome(pesquisa));
    	result.include("pesquisa", pesquisa);
    }
	@Path("/Visualizar/Produtor/{id}")
	public void visualizar(int id)
	{
		Produtor produtor  = null;
		try {
			produtor = produtorDAO.pesquisar(id);
			if (!produtor.isAtivo()) {
				result.redirectTo(ErrosController.class).erro_inativo();
			}
		} catch (Exception e) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		Cidade cidade = estadodao.pesquisa_cidade(Integer.parseInt(produtor.getCidade()));
		result.include("produtor",produtor);
		result.include("cidade", cidade);
	}
	@Post("/Deletar/Produtor")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		produtorDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Produtor/{id}")
	public void finalizado(int id)
	{
		result.include("produtor", produtorDAO.pesquisar(id));
	}
}
