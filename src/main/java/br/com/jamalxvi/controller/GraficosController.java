package br.com.jamalxvi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.jamalxvi.dao.ContasDAO;
import br.com.jamalxvi.dao.ExecutadoDAO;
import br.com.jamalxvi.dao.PenduraDAO;
import br.com.jamalxvi.infra.Soma_Dados_Contas_Mes;
import br.com.jamalxvi.seguranca.NivelFuncionario;

@Controller
public class GraficosController {

	private final Result result;
	private ExecutadoDAO executadoDAO;
	private ContasDAO contasDAO;
	private PenduraDAO penduraDAO;

	/**
	 * @deprecated CDI eyes only
	 */
	protected GraficosController() {
		this(null, null, null, null);
	}
	
	@Inject
	public GraficosController(Result result, ExecutadoDAO executadoDAO, ContasDAO contasDAO, PenduraDAO penduraDAO) {
		this.result = result;
		this.executadoDAO = executadoDAO;
		this.contasDAO = contasDAO;
		this.penduraDAO = penduraDAO;
	}

	@Path("/Graficos/Contas/")
	@NivelFuncionario(nivel=0)
	public void contas()
	{
		
	}
	@Path("/Graficos/Pendura/")
	@NivelFuncionario(nivel=0)
	public void pendura()
	{
		
	}
	@Path("/Graficos/Contagem/")
	public void contagem()
	{
		
	}
	@Path("/Graficos/Contagem/Total/")
	public void contagem_total()
	{
		
	}
	@Get("/Json/Graficos/Contas/{ano}/{data_escolhida}")
	@NivelFuncionario(nivel=0)
	public void listajson(int data_escolhida, int ano)
	{
		Soma_Dados_Contas_Mes mes = executadoDAO.soma_mes(data_escolhida, ano);
		mes.setConta_mes(contasDAO.soma_mes(data_escolhida, ano));
		result.use(Results.json()).withoutRoot().from(mes)
		.serialize();
	}
	@Get("/Json/Graficos/Pendura/{ano}/{data_escolhida}")
	@NivelFuncionario(nivel=0)
	public void lista_pendura_json(int data_escolhida, int ano)
	{
		Soma_Dados_Contas_Mes mes = new Soma_Dados_Contas_Mes();
		mes.setConta_mes(penduraDAO.soma_nao_pago(data_escolhida, ano));
		mes.setVenda_mes(penduraDAO.soma_pago(data_escolhida, ano));
		result.use(Results.json()).withoutRoot().from(mes)
		.serialize();
	}
	@Get("/Graficos/Contagem/{data_escolhida}/json")
	public void lista_contagem_json(int data_escolhida)
	{
	}
	
	@Get("/Graficos/Contagem/Total/json")
	public void lista_contagem_total_json()
	{
		List<Object> total = new ArrayList<>();
		for (int i = 1; i < 13; i++) {
			Object somas = new Object[]{

				};
			total.add(somas);
		}
		result.use(Results.json()).withoutRoot().from(total)
		.serialize();
	}
	
}