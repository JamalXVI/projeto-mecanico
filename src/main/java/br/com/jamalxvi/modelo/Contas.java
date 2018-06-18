package br.com.jamalxvi.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Contas implements Serializable{

	private static final long serialVersionUID = 452704007099799569L;
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dia;
	@NotEmpty
	private String motivo;
	@NotNull
	private BigDecimal valor;
	private boolean ativo;
	private boolean produto;
	private int quantidade;
	private String nome;
	private boolean paga;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dia_pago;
	private String observacao;
	private String duplicata;
	private String numero_nota;
	private boolean nota_fiscal;
	@ManyToOne
	private Produtor produtor;
	private boolean tem_produtor;
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Pecas peca;
	
	public boolean isTem_produtor() {
		return tem_produtor;
	}
	public void setTem_produtor(boolean tem_produtor) {
		this.tem_produtor = tem_produtor;
	}
	public Calendar getDia_pago() {
		return dia_pago;
	}
	public void setDia_pago(Calendar dia_pago) {
		this.dia_pago = dia_pago;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDuplicata() {
		return duplicata;
	}
	public void setDuplicata(String duplicata) {
		this.duplicata = duplicata;
	}
	public String getNumero_nota() {
		return numero_nota;
	}
	public void setNumero_nota(String numero_nota) {
		this.numero_nota = numero_nota;
	}
	public boolean isNota_fiscal() {
		return nota_fiscal;
	}
	public void setNota_fiscal(boolean nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}
	
	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	public boolean isPaga() {
		return paga;
	}
	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDia() {
		return dia;
	}
	public void setDia(Calendar dia) {
		this.dia = dia;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public boolean isProduto() {
		return produto;
	}
	public void setProduto(boolean produto) {
		this.produto = produto;
	}
	public Pecas getPeca() {
		return peca;
	}
	public void setPeca(Pecas peca) {
		this.peca = peca;
	}
	
}
