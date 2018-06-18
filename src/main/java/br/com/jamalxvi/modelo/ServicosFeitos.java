package br.com.jamalxvi.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class ServicosFeitos implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3415868553751449378L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Servicos servico;
	private int numero_horas;
	private BigDecimal preco;
	@ManyToOne
	private Orcamento orcamento;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Servicos getServico() {
		return servico;
	}
	public void setServico(Servicos servico) {
		this.servico = servico;
	}
	public int getNumero_horas() {
		return numero_horas;
	}
	public void setNumero_horas(int numero_horas) {
		this.numero_horas = numero_horas;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
}
