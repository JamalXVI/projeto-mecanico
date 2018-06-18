/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jamalxvi.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author henrique
 */
@Entity
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7977657403778698755L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	@OneToOne(mappedBy="cliente", cascade=CascadeType.ALL )
    private Identificacao identificacao;
    @NotBlank @NotNull @Size(max=255)
    private String nome;
    private String rua;
    private String num;
    private String comp;
    private String bai;
    private String informacao;
    private String cep;
    private String cidade;
    private String estado;
    private boolean ativo;
    @Email
	private String email;
    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
    private List<TelefoneCliente> telefones;
    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
    private List<IdentificacaoVeiculo> veiculos;
    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
    private List<FotoPerfil> fotos;
    @OneToMany(cascade=CascadeType.ALL)
    private List<ServicosExecutados> autorizados;
    
	public List<ServicosExecutados> getAutorizados() {
		return autorizados;
	}

	public void setAutorizados(List<ServicosExecutados> autorizados) {
		this.autorizados = autorizados;
	}

	public List<IdentificacaoVeiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<IdentificacaoVeiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<FotoPerfil> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoPerfil> fotos) {
		this.fotos = fotos;
	}

	public Identificacao getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Identificacao identificacao) {
		this.identificacao = identificacao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<TelefoneCliente> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneCliente> telefones) {
		this.telefones = telefones;
	}

	public Cliente() {
    }
    
    public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    
    
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

    public String getBai() {
        return bai;
    }

    public void setBai(String bai) {
        this.bai = bai;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
