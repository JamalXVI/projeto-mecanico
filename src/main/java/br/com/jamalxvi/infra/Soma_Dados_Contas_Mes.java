package br.com.jamalxvi.infra;

import java.math.BigDecimal;

public class Soma_Dados_Contas_Mes {
	private BigDecimal conta_mes;
	private BigDecimal venda_mes;
	public Soma_Dados_Contas_Mes()
	{
		venda_mes = new BigDecimal(0);
		conta_mes = new BigDecimal(0);
	}
	public BigDecimal getConta_mes() {
		return conta_mes;
	}
	public void setConta_mes(BigDecimal conta_mes) {
		this.conta_mes = conta_mes;
	}
	public BigDecimal getVenda_mes() {
		return venda_mes;
	}
	public void setVenda_mes(BigDecimal venda_mes) {
		this.venda_mes = venda_mes;
	}
	
}
