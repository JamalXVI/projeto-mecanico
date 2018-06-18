package br.com.jamalxvi.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Cidade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4857522705105717783L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	@ManyToOne
	private Estado estado;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
