package br.com.jamalxvi.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class PecasUsadas implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9078473080567224475L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private BigDecimal preco_compra;
	private BigDecimal preco_venda;
	@ManyToOne
	private Pecas peca;
	private int quantidade_usada;
	
	public int getQuantidade_usada() {
		return quantidade_usada;
	}
	public void setQuantidade_usada(int quantidade_usada) {
		this.quantidade_usada = quantidade_usada;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPreco_compra() {
		return preco_compra;
	}
	public void setPreco_compra(BigDecimal preco_compra) {
		this.preco_compra = preco_compra;
	}
	public BigDecimal getPreco_venda() {
		return preco_venda;
	}
	public void setPreco_venda(BigDecimal preco_venda) {
		this.preco_venda = preco_venda;
	}
	public Pecas getPeca() {
		return peca;
	}
	public void setPeca(Pecas peca) {
		this.peca = peca;
	}
	
	
}
