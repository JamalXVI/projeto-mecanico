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
import br.com.jamalxvi.dao.MarcaDAO;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class MarcaController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private MarcaDAO marcadao;
	
	@Inject
	public MarcaController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, MarcaDAO marcadao) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.marcadao = marcadao;
	}
	public MarcaController() {
		this(null, null, null, null);
	}
   
	@Path("/Cadastro/Marca/")
	public void form()
	{
		
		
	}
	@Post("/Cadastrar/Marca/")
	public void cadastrar(@Valid Marca marca)
	{
		validator.onErrorRedirectTo(this).form();
		
		marcadao.salvar(marca);
		result.redirectTo(this).finalizado(marcadao.decrescente());
	}
	@Get("/Json/Lista/Marca/")
	public void lista()
	{
		Collection<Marca> marcas = marcadao.listar();
		
		result.use(Results.json()).withoutRoot().from(marcas)
		.serialize();
	}
	@Get("/Finalizado/Marca/{id}")
	public void finalizado(int id)
	{
		result.include(marcadao.buscar_id(id));
	}
	@Get("/Lista/Marca")
    public void listar(String pesquisa)
    {
		result.include("marcas", marcadao.pesquisar_nome(pesquisa));
    }
	@Path("/Visualizar/Marca/{id}")
	public void visualizar(int id)
	{
		result.include("marca", marcadao.buscar_id(id));
	}
	@Post("/Deletar/Marca")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		marcadao.apagar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
}
