package br.com.jamalxvi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class IdentificacaoProdutor implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4439073477662235132L;
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Produtor produtor;
	private String cnpj;
	private String inscricao_estadual;
	
	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
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
