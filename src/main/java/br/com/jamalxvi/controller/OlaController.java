package br.com.jamalxvi.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.jamalxvi.dao.MarcaDAO;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.seguranca.LoginFuncionario;

@Controller
public class OlaController {

	private final Result result;
	private MarcaDAO marcadao;

	/**
	 * @deprecated CDI eyes only
	 */
	protected OlaController() {
		this(null, null);
	}
	
	@Inject
	public OlaController(Result result, MarcaDAO marcadao) {
		this.result = result;
		this.marcadao = marcadao;
	}
	@LoginFuncionario
	@Path("/as")
	public void index() {
		System.out.println("odssdsa");
		Marca m = new Marca();
		m.setNome("Volks");
		marcadao.salvar(m);
		System.out.println("odsa");
		result.nothing();
	}
}