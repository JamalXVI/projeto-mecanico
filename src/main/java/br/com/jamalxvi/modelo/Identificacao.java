package br.com.jamalxvi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Identificacao implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3141260391761498805L;
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Cliente cliente;
	private String cpf;
	private String rg;
	private String inscricao_municipal;
	private String tipo;
	private String cnpj;
	private String inscricao_estadual;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getInscricao_municipal() {
		return inscricao_municipal;
	}
	public void setInscricao_municipal(String inscricao_municipal) {
		this.inscricao_municipal = inscricao_municipal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}
	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}
	
}
