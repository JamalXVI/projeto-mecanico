package br.com.jamalxvi.controller;

import java.util.Collection;
import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jamalxvi.dao.FuncionarioDAO;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.seguranca.LoginFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class LoginController {
	
	private UsuarioLogado usuarioLogado;
	private Result result;
	private Validator validator;
	private FuncionarioDAO funcionariodao;

	@Inject
	public LoginController(UsuarioLogado usuarioLogado, Result result, Validator validator, FuncionarioDAO funcionariodao) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.funcionariodao = funcionariodao;
	}
	public LoginController() {
		this(null, null, null, null);
	}
	@LoginFuncionario
	public void form()
	{
		
	}
	@LoginFuncionario
	@Path("/Login/Deslogar/")
	public void deslogar()
	{
		usuarioLogado.desloga();
		result.redirectTo(this).form();
	}
	@LoginFuncionario @Post
	public void autentica(String usuario, String senha)
	{
		Collection<Funcionario> collection = funcionariodao.logou(usuario, senha);
			if (collection.size() > 0) {
				Funcionario f = collection.iterator().next();
				usuarioLogado.fazLogin(f);
				result.redirectTo(IndiceController.class).inicio();
			}else{
				validator.add(new SimpleMessage("login_invalido",
						"Login ou senha incorretos"));
				validator.onErrorRedirectTo(this).form();
			}
	}
}
