package br.com.jamalxvi.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.validation.Valid;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.ClienteDAO;
import br.com.jamalxvi.dao.EstadoDAO;
import br.com.jamalxvi.dao.FuncionarioDAO;
import br.com.jamalxvi.modelo.Cidade;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Estado;
import br.com.jamalxvi.modelo.FotoPerfil;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.TelefoneCliente;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class ClienteController 
{
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private EstadoDAO estadodao;
	private ClienteDAO clienteDAO;
	private FuncionarioDAO funcionariodao;
	
	@Inject 
	public ClienteController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, EstadoDAO estadodao, ClienteDAO clienteDAO, FuncionarioDAO funcionariodao) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.estadodao = estadodao;
		this.clienteDAO = clienteDAO;
		this.funcionariodao = funcionariodao;
	}
	
	public ClienteController() {
		this(null, null, null, null, null, null);
	}
   
	@Path("/Cadastro/Cliente/")
	public void form(Cliente cliente, List<TelefoneCliente> telefone)
	{
		
		Collection<Estado> estados = estadodao.listar();
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		result.include("estados", estados);
		result.include("funcionario", cliente);
		result.include("telefones", telefone);
	}
    @Get("/Editar/Cliente/{id}")
	public void editar(int id)
	{
    	if (funcionariodao.verificar_se_eh_funcionario(id) && usuarioLogado.getUsuario().getFuncao() > 1) {
			result.redirectTo(this).form(null, null);
		}
    	if (funcionariodao.verificar_se_eh_funcionario(id)) {
    		Funcionario funcionario = funcionariodao.cliente_funcionario(id);
			result.redirectTo(FuncionarioController.class).editar(funcionario.getId());
		}
		Collection<Estado> estados = estadodao.listar();
    	Cliente cliente = clienteDAO.pesquisar_id(id);
		for (Estado estado : estados) {
			System.out.println(estado.getNome() + "/" + estado.getUf());
			for (Cidade c : estado.getCidades()) {
				System.out.println("Cidade: "+c.getNome() + "/" + c.getEstado().getUf());
			}
		}
		System.out.println(cliente.getIdentificacao().getCpf());
		result.include("estados", estados);
		result.include("cliente", cliente);
	}
    @Get("/Json/Cliente/Telefones/{id}")
	public void lista_telefone(int id)
	{
		Cliente cliente = clienteDAO.pesquisar_id(id);
		List<TelefoneCliente> telefoneCliente = cliente.getTelefones();
		result.use(Results.json()).withoutRoot().from(telefoneCliente).include()
		.serialize();
	}
    @Get("/Json/Lista/Cliente")
	public void lista()
	{
		Collection<Cliente> clientes = clienteDAO.listar();
		
		result.use(Results.json()).withoutRoot().from(clientes).include("identificacao")
		.serialize();
	}
    @Get("/Lista/Cliente")
    public void listar(String pesquisa)
    {
    	result.include("clientes", clienteDAO.pesquisar_nome(pesquisa));
    	result.include("pesquisa", pesquisa);
    }
    @Get("/Lista/Cliente/Informacao")
    public void listar_informacao(String pesquisa)
    {
    	result.include("clientes", clienteDAO.pesquisar_informacao(pesquisa));
    	result.include("pesquisa", pesquisa);
    }
    @Get("/Lista/Cliente/Identidade")
    public void listar_identidade(String cpf, String cnpj, String tipo)
    {
		if (tipo != null) {
			if (!tipo.equals("")) {
				String pesquisa = "";
				if (tipo.equals("CPF")) {
					pesquisa = cpf;
				} else {
					pesquisa = cnpj;
				}
				result.include("clientes", clienteDAO.pesquisar_identidade(pesquisa));
				result.include("pesquisa", pesquisa);
			}
		}

    }
    @Get("/Json/Lista/Cliente/Veiculo")
	public void lista_veiculo()
	{
		Collection<Cliente> clientes = clienteDAO.listar();
		
		result.use(Results.json()).withoutRoot().from(clientes).include("identificacao").include("veiculos")
		.serialize();
	}
	@Post("/Cadastrar/Cliente/")
	@UploadSizeLimit(sizeLimit=40 * 1024 * 1024, fileSizeLimit=10 * 1024 * 1024)
	public void cadastrar(@Valid Cliente cliente, UploadedFile imagem,
			@Valid List<TelefoneCliente> telefone)
	{
		validator.onErrorRedirectTo(this).form(cliente, telefone);
		byte[] arquivo = null;
		if (imagem != null) {
			arquivo = mudar_perfil(imagem);
		}
		
		clienteDAO.salvar(cliente, arquivo, telefone);
		result.redirectTo(this).finalizado(clienteDAO.ultimo());
		
	}
	@Post("/Editado/Cliente/")
	@UploadSizeLimit(sizeLimit=40 * 1024 * 1024, fileSizeLimit=10 * 1024 * 1024)
	public void editado(@Valid Cliente cliente, UploadedFile imagem,
			@Valid List<TelefoneCliente> telefone)
	{
		
		validator.onErrorRedirectTo(this).editar(cliente.getId());
		clienteDAO.apagar_telefones(cliente.getId());
		byte[] arquivo = null;
		if (imagem != null) {
			arquivo = mudar_perfil(imagem);
			clienteDAO.apagar_imagens(cliente.getId());
		}
		
		clienteDAO.atualizar(cliente, arquivo, telefone);
		result.redirectTo(this).finalizado(cliente.getId());
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
	@Path("/Visualizar/Cliente/{id}")
	public void visualizar(int id)
	{
		Cliente cliente = clienteDAO.pesquisar_id(id);
		if (!cliente.isAtivo()) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		Cidade cidade = estadodao.pesquisa_cidade(Integer.parseInt(cliente.getCidade()));
		result.include("cliente",cliente);
		result.include("cidade", cidade);
	}
	@Path("/Imagem/Cliente/{id}/")  
    public Download imagem(int id) {  
    	Cliente cliente = clienteDAO.pesquisar_id(id);
    	byte[] imagem = null;
    	for (FotoPerfil f : cliente.getFotos()) {
			if (f.isAtivo()) {
				imagem = f.getImg();
			}
		}
    	if (imagem != null) {
    		return new ByteArrayDownload(imagem, "image/png", String.valueOf(id)); 
		}else{
			try {
				BufferedImage foto = ImageIO.read(new URL(getClass().getClassLoader().
				        getResource("/resources/imagens/"+"Desconhecido.png"), "Desconhecido.png"));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    	try {
					ImageIO.write( foto, "jpg", baos );
					baos.flush();
			    	byte[] image = baos.toByteArray();
			    	baos.close();
			    	return new ByteArrayDownload(image, "image/png", String.valueOf(id)); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return null;
				}
			} catch (MalformedURLException e) {
				e.getMessage();
				// TODO Auto-generated catch block
				return null;
			} catch (IOException e) {
				e.getMessage();
				return null;
			}
		}
    	
    	
         
    }  
	@Get("/Finalizado/Cliente/{id}")
	public void finalizado(int id)
	{
		Cliente cliente = clienteDAO.pesquisar_id(id);
		result.include("cliente",cliente);
	}
	@Post("/Deletar/Cliente")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		clienteDAO.apagar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	
}
