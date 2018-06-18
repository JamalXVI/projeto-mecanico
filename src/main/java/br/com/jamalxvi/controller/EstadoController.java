package br.com.jamalxvi.controller;


import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.EstadoDAO;
import br.com.jamalxvi.modelo.Cidade;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class EstadoController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	@SuppressWarnings("unused")
	private SimpleValidator validator;
	private EstadoDAO estadoDAO;
	
	@Inject
	public EstadoController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, EstadoDAO estadoDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.estadoDAO = estadoDAO;
	}
	public EstadoController() {
		this(null, null, null, null);
	}
	@Get("/Json/Pesquisa/Cidade/{id}")
	public void lista(int id)
	{
		Cidade cidade = estadoDAO.pesquisa_cidade(id);
		result.use(Results.json()).withoutRoot().from(cidade).include("estado")
		.serialize();
	}
	
}
