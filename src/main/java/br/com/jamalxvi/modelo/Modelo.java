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
public class Modelo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -578255505332864376L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nome;
	private boolean ativo;
	@ManyToOne
	private Marca marca;
	@OneToMany(mappedBy="modelo", cascade=CascadeType.ALL)
	private List<IdentificacaoVeiculo> veiculos;
	
	public List<IdentificacaoVeiculo> getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(List<IdentificacaoVeiculo> veiculos) {
		this.veiculos = veiculos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
