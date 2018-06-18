package br.com.jamalxvi.modelo;

import java.math.BigDecimal;
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
public class Fornecedor implements java.io.Serializable{
	/**
	 * Notas: O FORNECEDOR é o FABRICANTE, pois devido uma mudança de projeto, ocasionada pelo cliente, essa mudança passou a ser feita em 
	 * termos visuais.
	 */
	private static final long serialVersionUID = -403900475967187963L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nome;
	
	@OneToOne(mappedBy="fornecedor", cascade=CascadeType.ALL)
	private IdentificacaoFornecedor identificacao;
	@OneToMany(mappedBy="fornecedor", cascade=CascadeType.ALL)
	private List<TelefoneFornecedor> telefone;
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
	@NotNull
	private BigDecimal porcentagem;
	private boolean ativo;
	@Email
	private String email;
	@OneToMany(mappedBy="fornecedor", cascade=CascadeType.ALL)
	private List<Pecas> pecas;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public IdentificacaoFornecedor getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(IdentificacaoFornecedor identificacao) {
		this.identificacao = identificacao;
	}
	public List<Pecas> getPecas() {
		return pecas;
	}
	public void setPecas(List<Pecas> pecas) {
		this.pecas = pecas;
	}
	public List<TelefoneFornecedor> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<TelefoneFornecedor> telefone) {
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
	
	public BigDecimal getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
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
