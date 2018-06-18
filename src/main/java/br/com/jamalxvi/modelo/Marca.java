package br.com.jamalxvi.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Marca implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8166501677591093455L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nome;
	@OneToMany(mappedBy="marca")
	private List<Modelo> modelo;
	private boolean ativo;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Modelo> getModelo() {
		return modelo;
	}
	public void setModelo(List<Modelo> modelo) {
		this.modelo = modelo;
	}
	
}
