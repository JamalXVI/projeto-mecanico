package br.com.jamalxvi.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Servicos implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6344445661377965415L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nome;
	private boolean ativo;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ServicosFeitos> servicos_feitos;
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public List<ServicosFeitos> getServicos_feitos() {
		return servicos_feitos;
	}
	public void setServicos_feitos(List<ServicosFeitos> servicos_feitos) {
		this.servicos_feitos = servicos_feitos;
	}
	
}
