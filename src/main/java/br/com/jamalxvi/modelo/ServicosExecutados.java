package br.com.jamalxvi.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class ServicosExecutados implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5277827823120296330L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Funcionario mecanico;
	@ManyToOne
	private Cliente autorizador;
	private String responsavel;
	@OneToOne
	private Orcamento orcamento;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar entrega;
	private String forma_pagamento;
	private boolean ativo;
	private boolean pago;
	
	
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Funcionario getMecanico() {
		return mecanico;
	}
	public void setMecanico(Funcionario mecanico) {
		this.mecanico = mecanico;
	}
	public Cliente getAutorizador() {
		return autorizador;
	}
	public void setAutorizador(Cliente autorizador) {
		this.autorizador = autorizador;
	}
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public Calendar getEntrega() {
		return entrega;
	}
	public void setEntrega(Calendar entrega) {
		this.entrega = entrega;
	}
	public String getForma_pagamento() {
		return forma_pagamento;
	}
	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	
	
}
