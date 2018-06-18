package br.com.jamalxvi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplevalidator.SimpleValidator;
import br.com.jamalxvi.dao.ContasDAO;
import br.com.jamalxvi.dao.PecasDAO;
import br.com.jamalxvi.dao.ProdutorDAO;
import br.com.jamalxvi.modelo.Contas;
import br.com.jamalxvi.modelo.Pecas;
import br.com.jamalxvi.modelo.Produtor;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class ContasController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private PecasDAO pecasDAO;
	private ContasDAO contasDAO;
	private ProdutorDAO produtorDAO;
	@Inject
	public ContasController(UsuarioLogado usuarioLogado, SimpleValidator validator,
			Result result, PecasDAO pecasDAO, ContasDAO contasDAO, ProdutorDAO produtorDAO)
	{
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
		this.pecasDAO = pecasDAO;
		this.contasDAO = contasDAO;
		this.produtorDAO = produtorDAO;
		
	}
	public ContasController()
	{
		this(null,null,null, null, null, null);
	}
	@Path("/Cadastro/Contas/")
	@NivelFuncionario(nivel=0)
	public void form()
	{
		result.include("produtos", pecasDAO.listar());
		result.include("produtores", produtorDAO.listar());
	}
	@Post("/Cadastrar/Contas/")
	@NivelFuncionario(nivel=0)
	public void cadastrar(@Valid Contas contas, @Valid @NotNull int produto_id, @Valid @NotEmpty String dia_contas, @Valid @NotNull int produtor_id)
	{
		validator.onErrorRedirectTo(this).form();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(dia_contas));
		} catch (ParseException e) {
			result.redirectTo(this).form();
			return;
		}
		contas.setDia(calendar);
		Pecas peca = new Pecas();
		if (contas.isProduto()) {
			peca = pecasDAO.pesquisar(produto_id);
			peca.setEstoque(peca.getEstoque()+contas.getQuantidade());
			pecasDAO.atualizar(peca);
		}
		Produtor produtor = null;
		if (contas.isTem_produtor()) {
			produtor = produtorDAO.pesquisar(produtor_id);
		}
		contasDAO.salvar(contas, peca, produtor);
		result.redirectTo(this).finalizado(contasDAO.ultimo());
	}
	@Get("/Lista/Contas")
	@NivelFuncionario(nivel=0)
	public void lista(int data_escolhida, int ano)
	{
		result.include("contas",contasDAO.pesquisar_data(data_escolhida, ano));
	}
	@Get("/Lista/Contas/Data")
	@NivelFuncionario(nivel=0)
	public void lista_data(String inicio, String fim)
	{
		result.include("contas",contasDAO.pesquisar_dias(inicio, fim));
	}
	@Post("/Deletar/Contas")
	@NivelFuncionario(nivel=0)
	public void deletar(int id_esc_deletar)
	{
		contasDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Contas/{id}")
	@NivelFuncionario(nivel=0)
	public void finalizado(int id)
	{
		result.include("conta", contasDAO.pesquisar(id));
	}
	@Post("/Pagar/Contas/")
	@NivelFuncionario(nivel=0)
	public void pagar(@Valid @NotNull int id_conta_pagar, String observacao, @Valid @NotBlank @NotEmpty String data_pago)
	{
		validator.onErrorRedirectTo(ErrosController.class).erro_operacao();
	    Contas contas = contasDAO.pesquisar(id_conta_pagar);
		if (!contas.isAtivo()) {
			result.redirectTo(ErrosController.class).erro_inativo();
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(data_pago));
		} catch (ParseException e) {
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}
		contas = contasDAO.pagar_completo(contas, observacao, calendar);
		result.include("conta", contas);
	}
}