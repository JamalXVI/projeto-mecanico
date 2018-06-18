package br.com.jamalxvi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import javax.inject.Inject;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.ClienteDAO;
import br.com.jamalxvi.dao.ExecutadoDAO;
import br.com.jamalxvi.dao.MarcaDAO;
import br.com.jamalxvi.dao.OrcamentoDAO;
import br.com.jamalxvi.dao.SolicitacaoDAO;
import br.com.jamalxvi.dao.VeiculoDAO;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.IdentificacaoVeiculo;
import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.modelo.Orcamento;
import br.com.jamalxvi.modelo.Solicitacoes;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
public class SolicitacaoController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private VeiculoDAO veiculodao;
	private MarcaDAO marcadao;
	private ClienteDAO clientedao;
	private SolicitacaoDAO solicitacaodao;
	private OrcamentoDAO orcamentoDAO;
	private ExecutadoDAO executadoDAO;
	
	@Inject
	public SolicitacaoController(UsuarioLogado usuarioLogado,
			Result result, SimpleValidator validator, VeiculoDAO veiculodao, MarcaDAO marcadao,
			ClienteDAO clientedao, SolicitacaoDAO solicitacaodao, OrcamentoDAO orcamentoDAO, ExecutadoDAO executadoDAO) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.veiculodao = veiculodao;
		this.marcadao = marcadao;
		this.clientedao = clientedao;
		this.solicitacaodao = solicitacaodao;
		this.orcamentoDAO = orcamentoDAO;
		this.executadoDAO = executadoDAO;
	}
	public SolicitacaoController() {
		this(null, null, null, null, null, null, null, null, null);
	}
	@Get("/Cadastro/Solicitacao/{id}")
	public void cadastrar_v(int id)
	{
		IdentificacaoVeiculo veiculo = veiculodao.pesquisar(id);
		result.include("veiculo", veiculo);
		result.redirectTo(this).form();
	}
	@Path("/Cadastro/Solicitacao/")
	public void form()
	{
		Collection<Marca> marcas = marcadao.listar();
		Collection<Cliente> clientes = clientedao.listar();
		result.include("marcas", marcas);
		result.include("clientes", clientes);
	}
	
	@Path("/Json/Lista/Solicitacao/Ativas/")
	public void lista_ativas()
	{
		Collection<Solicitacoes> ativas = solicitacaodao.listar_ativas();
		result.use(Results.json()).withoutRoot().from(ativas).include("veiculo")
		.serialize();
	}
	@Path("/Json/Lista/Solicitacao/Inativas/")
	public void lista_inativas()
	{
		Collection<Solicitacoes> ativas = solicitacaodao.listar_inativas();
		result.use(Results.json()).withoutRoot().from(ativas).include("veiculo")
		.serialize();
	}
	@Post("/Cadastrar/Solicitacao/")
	@UploadSizeLimit(sizeLimit=1000 * 1024 * 1024, fileSizeLimit=1000 * 1024 * 1024)
	public void cadastrar(@Valid Solicitacoes solicitacao, int id_veiculo, int id_cliente, UploadedFile[] imagem, @Valid @NotEmpty String horario)
	{
		validator.onErrorRedirectTo(this).form();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(horario));
		} catch (ParseException e) {
			result.redirectTo(this).form();
			return;
		}
		solicitacao.setData_entrada(calendar);
		IdentificacaoVeiculo veiculo = veiculodao.pesquisar(id_veiculo);
		solicitacao.setVeiculo(veiculo);
		solicitacaodao.salvar(solicitacao, imagem);
		result.redirectTo(this).finalizado(solicitacaodao.decrescente());
	}
	@Post("/Deletar/Solicitacao")
	@NivelFuncionario(nivel=1)
	public void deletar(int id_esc_deletar)
	{
		Solicitacoes solicitacoes = solicitacaodao.deletar(id_esc_deletar);
		if (solicitacoes.isAtendida()) {
			Orcamento orcamento = orcamentoDAO.pesquisa(orcamentoDAO.pesquisa_solicitacao(id_esc_deletar));
			orcamentoDAO.deletar(orcamento.getId());
			if (orcamento.isAtendido()) {
				int executado = executadoDAO.pesquisa_orcamento(orcamento.getId());
				executadoDAO.deletar(executado);
			}
		}
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Solicitacao/{id}")
	public void finalizado(int id)
	{
		Solicitacoes solicitacoes = solicitacaodao.pesquisar(id);
		result.include("solicitacao", solicitacoes);
	}
	@Get("/Lista/Solicitacao")
	public void listar(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		result.include("solicitacoes", solicitacaodao.pesquisar_nome(pesquisa));
	}
	@Get("/Lista/Solicitacao/Id")
	public void listar_id(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		result.include("solicitacoes", solicitacaodao.pesquisar_id(pesquisa));
	}
}
