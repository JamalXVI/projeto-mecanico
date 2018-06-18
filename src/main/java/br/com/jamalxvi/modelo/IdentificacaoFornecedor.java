package br.com.jamalxvi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class IdentificacaoFornecedor implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4439073477662235132L;
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Fornecedor fornecedor;
	private String cnpj;
	private String inscricao_estadual;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
