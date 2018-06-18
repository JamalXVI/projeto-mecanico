package br.com.jamalxvi.controller;

import java.io.IOException;
import java.io.InputStream;
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
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.EstadoDAO;
import br.com.jamalxvi.dao.FuncionarioDAO;
import br.com.jamalxvi.modelo.Cidade;
import br.com.jamalxvi.modelo.Estado;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.TelefoneCliente;
import br.com.jamalxvi.seguranca.LoginFuncionario;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class FuncionarioController 
{
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private EstadoDAO estadodao;
	private FuncionarioDAO funcionariodao;
	
	@Inject
	public FuncionarioController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, EstadoDAO estadodao, FuncionarioDAO funcionariodao) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.estadodao = estadodao;
		this.funcionariodao = funcionariodao;
	}
	public FuncionarioController() {
		this(null, null, null, null, null);
	}
	@LoginFuncionario
    @Path("/Cadastro/Funcionario/")
    @NivelFuncionario(nivel=1)
	public void form(Funcionario funcionario, List<TelefoneCliente> telefone)
	{
		
		Collection<Estado> estados = estadodao.listar();
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		result.include("estados", estados);
		result.include("funcionario", funcionario);
		result.include("telefones", telefone);
	}
    @Path("/Editar/Funcionario/{id}")
    @NivelFuncionario(nivel=1)
	public void editar(int id)
	{
    	
    	Funcionario funcionario = funcionariodao.pesquisar_id(id);
    	if (usuarioLogado.getUsuario().getFuncao() > funcionario.getFuncao()) {
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}else if (funcionario.getId() != usuarioLogado.getUsuario().getId())
		{
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}
		Collection<Estado> estados = estadodao.listar();
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		System.out.println(funcionario.getCliente().getIdentificacao().getCpf());
		result.include("estados", estados);
		result.include("funcionario", funcionario);
	}
    @Get("/Json/Funcionario/Telefones/{id}")
	public void lista_telefone(int id)
	{
		Funcionario funcionario = funcionariodao.pesquisar_id(id);
		List<TelefoneCliente> telefoneCliente = funcionario.getCliente().getTelefones();
		result.use(Results.json()).withoutRoot().from(telefoneCliente).include()
		.serialize();
	}
    @LoginFuncionario
	@Post("/Cadastrar/Funcionario/")
	@UploadSizeLimit(sizeLimit=40 * 1024 * 1024, fileSizeLimit=10 * 1024 * 1024)
	public void cadastrar(@Valid Funcionario funcionario, UploadedFile imagem,
			@Valid List<TelefoneCliente> telefone)
	{
    	if (usuarioLogado.getUsuario().getFuncao() > funcionario.getFuncao()) {
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}
		validator.onErrorRedirectTo(this).form(funcionario, telefone);
		byte[] arquivo = null;
		if (imagem != null) {
			arquivo = mudar_perfil(imagem);
		}
		
		funcionariodao.salvar(funcionario, arquivo, telefone);
		result.redirectTo(this).finalizado(funcionariodao.ultimo().getId());
	}
	@Post("/Editado/Funcionario/")
	@UploadSizeLimit(sizeLimit=40 * 1024 * 1024, fileSizeLimit=10 * 1024 * 1024)
	@NivelFuncionario(nivel=1)
	public void editado(@Valid Funcionario funcionario, UploadedFile imagem,
			@Valid List<TelefoneCliente> telefone)
	{
		if (usuarioLogado.getUsuario().getFuncao() > funcionario.getFuncao()) {
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}else if (funcionario.getId() != usuarioLogado.getUsuario().getId())
		{
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}
		validator.onErrorRedirectTo(this).editar(funcionario.getId());;
		funcionariodao.apagar_telefones(funcionario.getId());
		byte[] arquivo = null;
		if (imagem != null) {
			arquivo = mudar_perfil(imagem);
			funcionariodao.apagar_imagens(funcionario.getId());
		}
		
		funcionariodao.atualizar(funcionario, arquivo, telefone);
		result.redirectTo(this).finalizado(funcionario.getId());
		
	}
	private byte[] mudar_perfil(UploadedFile imagem) {
		if (imagem == null) {
			return null;
		}
		//--- getters and setters
		long size = imagem.getSize();
	    System.out.println("File size: " + size);  

	    InputStream stream = imagem.getFile();
	    byte[] buffer = new byte[(int) size];  
	    try {
			stream.read(buffer, 0, (int) size);
		    stream.close();
		    return buffer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.redirectTo(this).form(null, null);
			return null;
		}  
		
	}
	@Path("/Json/Lista/Funcionario/")
	public void lista()
	{
		Collection<Funcionario> funcionarios = funcionariodao.lista();
		result.use(Results.json()).withoutRoot().from(funcionarios).include("cliente").exclude("senha")
		.serialize();
	}
	@Get("/Lista/Funcionario")
	@NivelFuncionario(nivel=2)
    public void listar(String pesquisa)
    {
    	result.include("funcionarios", funcionariodao.lista());
    	result.include("pesquisa", pesquisa);
    }
	@Post("/Deletar/Funcionario")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		funcionariodao.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Funcionario/{id}")
	public void finalizado(int id)
	{
		result.include("funcionario", funcionariodao.pesquisar_id(id));
	}
	@Path("/Visualizar/Funcionario/{id}")
	@NivelFuncionario(nivel=2)
	public void visualizar(int id)
	{
		Funcionario funcionario = null;
		try {
			funcionario = funcionariodao.pesquisar_id(id);
			if (!funcionario.isAtivo()) {
				result.redirectTo(ErrosController.class).erro_inativo();
			}
		} catch (Exception e) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		Cidade cidade = estadodao.pesquisa_cidade(Integer.parseInt(funcionario.getCliente().getCidade()));
		result.include("funcionario",funcionario);
		result.include("cidade", cidade);
	}
}
