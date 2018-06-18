package br.com.jamalxvi.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Fotos_Solicitacoes implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5868157182562828429L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "img", unique = false, nullable = false, length = 41943040)
	private byte[] img;
	@ManyToOne
	private Solicitacoes solicitacoes;
	private boolean ativo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public Solicitacoes getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(Solicitacoes solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
