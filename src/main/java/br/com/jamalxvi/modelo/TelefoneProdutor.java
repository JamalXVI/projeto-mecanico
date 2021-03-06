package br.com.jamalxvi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
@Entity
public class TelefoneProdutor implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8376510848605563128L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Size(max=2)
	private String ddd;
	@Size(max=10)
	private String numero;
	private String tipo;
	private String operadora;
	private boolean ativo;
	@ManyToOne
	private Produtor produtor;
	
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
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	
	
}
