package br.com.jamalxvi.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Produtor implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3737946477087109486L;
	/**
	 * 
	 */
	/**
	 *ESTE É O REAL FORNECEDOR!!!
	 *
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nome;
	
	@OneToOne(mappedBy="produtor", cascade=CascadeType.ALL)
	private IdentificacaoProdutor identificacao;
	@OneToMany(mappedBy="produtor", cascade=CascadeType.ALL)
	private List<TelefoneProdutor> telefone;
	//Dias
	@NotNull
	private int condicao_pagamento;
    private String rua;
    private String num;
    private String comp;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	private boolean ativo;
	@Email
	private String email;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="produtor")
	private List<Contas> contas;
	
	public List<Contas> getContas() {
		return contas;
	}
	public void setContas(List<Contas> contas) {
		this.contas = contas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public IdentificacaoProdutor getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(IdentificacaoProdutor identificacao) {
		this.identificacao = identificacao;
	}
	public List<TelefoneProdutor> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<TelefoneProdutor> telefone) {
		this.telefone = telefone;
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
	public int getCondicao_pagamento() {
		return condicao_pagamento;
	}
	public void setCondicao_pagamento(int condicao_pagamento) {
		this.condicao_pagamento = condicao_pagamento;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
