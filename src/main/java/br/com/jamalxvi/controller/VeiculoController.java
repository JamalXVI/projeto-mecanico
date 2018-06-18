package br.com.jamalxvi.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
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
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.ClienteDAO;
import br.com.jamalxvi.dao.MarcaDAO;
import br.com.jamalxvi.dao.ModeloDAO;
import br.com.jamalxvi.dao.VeiculoDAO;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Fotos_Solicitacoes;
import br.com.jamalxvi.modelo.IdentificacaoVeiculo;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.modelo.Modelo;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class VeiculoController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private VeiculoDAO veiculodao;
	private MarcaDAO marcadao;
	private ClienteDAO clientedao;
	private ModeloDAO modelodao;
	
	@Inject
	public VeiculoController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, VeiculoDAO veiculodao, MarcaDAO marcadao, ClienteDAO clientedao, ModeloDAO modelodao) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.veiculodao = veiculodao;
		this.marcadao = marcadao;
		this.clientedao = clientedao;
		this.modelodao = modelodao;
	}
	public VeiculoController() {
		this(null, null, null, null, null, null, null);
	}
	@Path("/Cadastro/Veiculo/M/{id}")
	public void cadastro_modelo(int id)
	{
		Modelo modelo = modelodao.pesquisar_id(id);
		result.include("modelo_a", modelo);
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Veiculo/{id}")
	public void cadastrar_c(int id)
	{
		Cliente cliente = clientedao.pesquisar_id(id);
		result.include("cliente",cliente);
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Veiculo/")
	public void form()
	{
		Collection<Marca> marcas = marcadao.listar();
		Collection<Cliente> clientes = clientedao.listar();
		result.include("marcas", marcas);
		result.include("clientes", clientes);
	}
	@Post("/Cadastrar/Veiculo/")
	public void cadastrar(@Valid IdentificacaoVeiculo veiculo, int id_modelo, int id_cliente)
	{
		
		validator.onErrorRedirectTo(this).form();
		Modelo modelo = modelodao.pesquisar_id(id_modelo);
		Cliente cliente = clientedao.pesquisar_id(id_cliente);
		veiculodao.salvar(veiculo, cliente, modelo);
		result.redirectTo(this).finalizado(veiculodao.pegar_ultimo().getId());
	}
	@Get("/Json/Lista/Veiculo/")
	public void lista()
	{
		Collection<IdentificacaoVeiculo> veiculos = veiculodao.listar();
		
		result.use(Results.json()).withoutRoot().from(veiculos).include("cliente").include("modelo")
		.serialize();
	}
	@Post("/Deletar/Veiculo")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		veiculodao.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Path("/Visualizar/Veiculo/{id}")
	public void visualizar(int id)
	{
		IdentificacaoVeiculo veiculo = null;
		try {
			veiculo = veiculodao.pesquisar(id);
			if (!veiculo.isAtivo()) {
				result.redirectTo(ErrosController.class).erro_inativo();
			}
		} catch (Exception e) {
			result.redirectTo(ErrosController.class).erro_inativo();
		}
		
		result.include("veiculo",veiculo);
	}
	@Path("/Imagem/Solicitacoes/{id}/")  
    public Download imagem(int id) {  
    	Fotos_Solicitacoes solicitacoes = veiculodao.pesquisar_imagem(id);
    	byte[] imagem = null;
		imagem = solicitacoes.getImg();
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
	@Get("/Finalizado/Veiculo/{id}")
	public void finalizado(int id)
	{
		IdentificacaoVeiculo veiculo = veiculodao.pesquisar(id);
		result.include("veiculo", veiculo);
	}
	@Get("/Lista/Veiculos")
    public void listar(String pesquisa)
    {
    	result.include("veiculos", veiculodao.pesquisar_placa(pesquisa));
    	result.include("pesquisa", pesquisa);
    }
}