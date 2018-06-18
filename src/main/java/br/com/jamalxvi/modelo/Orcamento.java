package br.com.jamalxvi.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orcamento implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153880438642747069L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToMany(cascade=CascadeType.ALL)
	private List<PecasUsadas> pecas;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ServicosFeitos> servicos;
	@OneToOne(mappedBy="orcamento", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Solicitacoes solicitacao;
	public Solicitacoes getSolicitacao() {
		return solicitacao;
	}
	public void setSolicitacao(Solicitacoes solicitacao) {
		this.solicitacao = solicitacao;
	}
	private boolean atendido;
	private boolean ativo;
	private BigDecimal total;
	private BigDecimal desconto;
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="orcamento")
	private ServicosExecutados executados;
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public BigDecimal getTotal() {
		return total;
	}
	
	public ServicosExecutados getExecutados() {
		return executados;
	}
	public void setExecutados(ServicosExecutados executados) {
		this.executados = executados;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<PecasUsadas> getPecas() {
		return pecas;
	}
	public void setPecas(List<PecasUsadas> pecas) {
		this.pecas = pecas;
	}
	public List<ServicosFeitos> getServicos() {
		return servicos;
	}
	public void setServicos(List<ServicosFeitos> servicos) {
		this.servicos = servicos;
	}
	
	public boolean isAtendido() {
		return atendido;
	}
	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}
	
	
	
}
