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
import br.com.jamalxvi.dao.ModeloDAO;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.modelo.Modelo;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class ModeloController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private MarcaDAO marcadao;
	private ModeloDAO modelodao;
	
	@Inject
	public ModeloController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, MarcaDAO marcadao, ModeloDAO modelodao) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.marcadao = marcadao;
		this.modelodao = modelodao;
	}
	public ModeloController() {
		this(null, null, null, null, null);
	}
	@Path("/Cadastro/Modelo/{id}")
	public void cadastro_marca(int id)
	{
		result.include("marca_a", marcadao.buscar_id(id));
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Modelo/")
	public void form()
	{
		Collection<Marca> marcas = marcadao.listar();
		result.include("marcas", marcas);
	}
	@Post("/Cadastrar/Modelo/")
	public void cadastrar(@Valid Modelo modelo, int id)
	{
		validator.onErrorRedirectTo(this).form();
		Marca marca = marcadao.buscar_id(id);
		modelo.setMarca(marca);
		modelo.setAtivo(true);
		modelodao.salvar(modelo);
		result.redirectTo(this).finalizado(modelodao.ultimo());
	}
	@Get("/Json/Lista/Modelo/")
	public void lista()
	{
		Collection<Modelo> modelos = modelodao.listar();
		
		result.use(Results.json()).withoutRoot().from(modelos).include("veiculos").include("marca")
		.serialize();
	}
	@Get("/Finalizado/Modelo/{id}")
	public void finalizado(int id)
	{
		result.include("modelo", modelodao.pesquisar_id(id));
	}
	@Post("/Deletar/Modelo")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		modelodao.apagar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Lista/Modelo")
    public void listar(String pesquisa)
    {
		result.include("modelos", modelodao.pesquisar_nome(pesquisa));
    }
}
