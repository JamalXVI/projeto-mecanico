package br.com.jamalxvi.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Solicitacoes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6597967124476876214L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private IdentificacaoVeiculo veiculo;
	private String solicitacoes;
	@OneToMany(mappedBy="solicitacoes", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Fotos_Solicitacoes> fotos;
	@OneToOne
	private Orcamento orcamento;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_entrada;
	private boolean atendida;
	private boolean ativo;
	
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtendida() {
		return atendida;
	}

	public void setAtendida(boolean atendida) {
		this.atendida = atendida;
	}

	public int getId() {
		return id;
	}
	
	public List<Fotos_Solicitacoes> getFotos() {
		return fotos;
	}

	public void setFotos(List<Fotos_Solicitacoes> fotos) {
		this.fotos = fotos;
	}

	public void setId(int id) {
		this.id = id;
	}
	public IdentificacaoVeiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(IdentificacaoVeiculo veiculo) {
		this.veiculo = veiculo;
	}
	

	public String getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(String solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Calendar getData_entrada() {
		return data_entrada;
	}
	public void setData_entrada(Calendar data_entrada) {
		this.data_entrada = data_entrada;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	
	
	/*
	 * Deixar um campo com 8 linhas para descricao de Servicos Executados;
	 * e 20 Linhas para peças;
	 * 1 linha executados por
	 * date que foi entregue;
	 * Autorizados por;
	 */
	
}
