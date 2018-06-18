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
import br.com.jamalxvi.dao.PenduraDAO;
import br.com.jamalxvi.dao.ClienteDAO;
import br.com.jamalxvi.dao.ExecutadoDAO;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.Pendura;
import br.com.jamalxvi.modelo.ServicosExecutados;
import br.com.jamalxvi.seguranca.NivelFuncionario;
import br.com.jamalxvi.seguranca.UsuarioLogado;

@Controller
@RequestScoped
public class PenduraController 
{
	@SuppressWarnings("unused")
	private UsuarioLogado usuarioLogado;
	private Result result;
	private SimpleValidator validator;
	private ClienteDAO clienteDAO;
	private PenduraDAO penduraDAO;
	private ExecutadoDAO executadoDAO;
	@Inject
	public PenduraController(UsuarioLogado usuarioLogado, SimpleValidator validator,
			Result result, ClienteDAO clienteDAO, PenduraDAO penduraDAO, ExecutadoDAO executadoDAO)
	{
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
		this.clienteDAO = clienteDAO;
		this.penduraDAO = penduraDAO;
		this.executadoDAO = executadoDAO;
		
	}
	public PenduraController()
	{
		this(null,null,null, null, null, null);
	}
	@Path("/Cadastro/Pendura/E/{id_executado}")
	@NivelFuncionario(nivel=0)
	public void cadastro_executado(int id_executado)
	{
		ServicosExecutados executados = executadoDAO.pesquisa(id_executado);
		if (executados.isPago()) {
			result.redirectTo(ErrosController.class).erro_operacao();
			return;
		}
		result.include("executado", executados);
		result.include("execucao", true);
	}
	@Path("/Cadastro/Pendura/")
	@NivelFuncionario(nivel=0)
	public void form()
	{
		result.include("clientes", clienteDAO.listar());
	}
	@Post("/Cadastrar/Pendura/")
	@NivelFuncionario(nivel=0)
	public void cadastrar(@Valid Pendura pendura, @Valid @NotEmpty String dia_pendura, @Valid @NotNull int id_cliente, boolean execucao, int execucao_id)
	{
		validator.onErrorRedirectTo(this).form();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(dia_pendura));
		} catch (ParseException e) {
			result.redirectTo(this).form();
			return;
		}
		pendura.setDia_para_pagar(calendar);
		Cliente cliente = clienteDAO.pesquisar_id(id_cliente);
		ServicosExecutados executados = null;
		if (execucao) {
			executados = executadoDAO.pesquisa(execucao_id);
		}
		penduraDAO.salvar(pendura, cliente, execucao, executados);
		result.redirectTo(this).finalizado(penduraDAO.ultimo());
	}
	@Get("/Lista/Pendura")
	@NivelFuncionario(nivel=0)
	public void lista(int data_escolhida, int ano)
	{
		result.include("penduras",penduraDAO.pesquisar_data(data_escolhida, ano));
	}
	@Get("/Lista/Pendura/Data")
	@NivelFuncionario(nivel=0)
	public void lista_data(String inicio, String fim)
	{
		result.include("penduras",penduraDAO.pesquisar_dias(inicio, fim));
	}
	@Get("/Lista/Pendura/NotaFiscal/Data")
	@NivelFuncionario(nivel=0)
	public void lista_data_nf(String inicio, String fim)
	{
		result.include("penduras",penduraDAO.pesquisar_dias_nf(inicio, fim));
	}
	@Get("/Lista/Pendura/NotaFiscal")
	@NivelFuncionario(nivel=0)
	public void lista_nf(int data_escolhida, int ano)
	{
		result.include("penduras",penduraDAO.pesquisar_data_nf(data_escolhida, ano));
	}
	@Get("/Lista/Pendura/Cliente/{id}")
	@NivelFuncionario(nivel=0)
	public void lista_cliente(int id)
	{
		result.include("cliente", clienteDAO.pesquisar_id(id));
		result.include("penduras",penduraDAO.pesquisar_cliente(id));
	}
	@Get("/Lista/Pendura/Execucao/{id}")
	@NivelFuncionario(nivel=0)
	public void lista_execucao(int id)
	{
		result.include("executado", executadoDAO.pesquisa(id));
		result.include("penduras",penduraDAO.pesquisar_execucao(id));
	}
	@Post("/Deletar/Pendura")
	@NivelFuncionario(nivel=0)
	public void deletar(int id_esc_deletar)
	{
		penduraDAO.deletar(id_esc_deletar);
		result.redirectTo(ErrosController.class).removido();
	}
	@Get("/Finalizado/Pendura/{id}")
	@NivelFuncionario(nivel=0)
	public void finalizado(int id)
	{
		result.include("pendura", penduraDAO.pesquisar(id));
	}
	@Post("/Pagar/Pendura/")
	@NivelFuncionario(nivel=0)
	public void pagar(@Valid @NotNull int id_pendura_pagar, String observacao, @Valid @NotBlank @NotEmpty String data_pago)
	{
		validator.onErrorRedirectTo(ErrosController.class).erro_operacao();
		Pendura pendura = penduraDAO.pesquisar(id_pendura_pagar);
		if (!pendura.isAtivo()) {
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
		pendura = penduraDAO.pagar_completo(pendura, observacao, calendar);
		result.include("pendura", pendura);
	}
	
}