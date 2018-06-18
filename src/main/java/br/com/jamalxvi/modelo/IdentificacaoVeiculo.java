package br.com.jamalxvi.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class IdentificacaoVeiculo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -624946863886661680L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne()
	private Modelo modelo;
	/*
	 O tipo se é caminhão, se é carro, se é van, o que que é;
	 */
	@NotEmpty
	private String tipo;
	@NotEmpty
	private String placa;
	/**
	 Cadastro Cliente
	 */
	@ManyToOne
	private Cliente cliente;
	@NotEmpty
	private String cor;
	//Ano de Fabricação
	@NotNull
	private int ano;
	private boolean ativo;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="veiculo")
	private List<Solicitacoes> solicitacoes;
	
	public List<Solicitacoes> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(List<Solicitacoes> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
