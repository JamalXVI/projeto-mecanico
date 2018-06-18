package br.com.jamalxvi.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pendura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8777238179769767906L;
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;	
	@ManyToOne
	private Cliente cliente;
	@NotNull
	private BigDecimal valor;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dia_para_pagar;
	@NotEmpty
	private String descricao;
	private boolean ativo;
	private boolean pago;
	private boolean execucao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dia_pago;
	private String observacao;
	private String duplicata;
	private String numero_nota;
	private boolean nota_fiscal;
	
	
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public boolean isNota_fiscal() {
		return nota_fiscal;
	}
	public String getNumero_nota() {
		return numero_nota;
	}
	public void setNumero_nota(String numero_nota) {
		this.numero_nota = numero_nota;
	}
	public void setNota_fiscal(boolean nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}
	public Calendar getDia_pago() {
		return dia_pago;
	}
	public void setDia_pago(Calendar dia_pago) {
		this.dia_pago = dia_pago;
	}
	public String getDuplicata() {
		return duplicata;
	}
	public void setDuplicata(String duplicata) {
		this.duplicata = duplicata;
	}
	@ManyToOne
	private ServicosExecutados servicosExecutados;
	
	
	
	public boolean isExecucao() {
		return execucao;
	}
	public void setExecucao(boolean execucao) {
		this.execucao = execucao;
	}
	public ServicosExecutados getServicosExecutados() {
		return servicosExecutados;
	}
	public void setServicosExecutados(ServicosExecutados servicosExecutados) {
		this.servicosExecutados = servicosExecutados;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Calendar getDia_para_pagar() {
		return dia_para_pagar;
	}
	public void setDia_para_pagar(Calendar dia_para_pagar) {
		this.dia_para_pagar = dia_para_pagar;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
