package br.com.jamalxvi.controller;

import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class ErrosController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	@SuppressWarnings("unused")
	private Result result;
	@SuppressWarnings("unused")
	private Validator validator;
	@Inject
	public ErrosController(UsuarioLogado usuarioLogado,
			Result result, Validator validator) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
	}
	public ErrosController() {
		this(null, null, null);
	}
	@Path("/Erros/Acesso/")
	public void erro_acesso()
	{
	
	}
	@Path("/Deletar/Sucesso/")
	public void removido()
	{
	
	}
	@Path("/Erros/Inativo/")
	public void erro_inativo()
	{
	
	}
	@Path("/Erros/Operacao/")
	public void erro_operacao()
	{
	
	}
	public void erro404()
	{
		System.out.println("dasdas");
	}
}
