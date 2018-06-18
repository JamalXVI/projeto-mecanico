package br.com.jamalxvi.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Pecas implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7594650470063293818L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Fornecedor fornecedor;
	@NotEmpty
	private String nome;
	private int estoque;
	@NotEmpty
	private String cod_fiscal;
	private boolean ativo;
	@OneToMany(cascade=CascadeType.ALL)
	private List<PecasUsadas> pecas_usadas;
	
	public String getCod_fiscal() {
		return cod_fiscal;
	}
	public void setCod_fiscal(String cod_fiscal) {
		this.cod_fiscal = cod_fiscal;
	}
	public List<PecasUsadas> getPecas_usadas() {
		return pecas_usadas;
	}
	public void setPecas_usadas(List<PecasUsadas> pecas_usadas) {
		this.pecas_usadas = pecas_usadas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
