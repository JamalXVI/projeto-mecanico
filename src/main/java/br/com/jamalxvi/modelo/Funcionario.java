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

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author henrique
 */
@Entity
public class Funcionario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4936434517036231231L;
	@NotNull @NotEmpty
    private String usuario;
	@NotEmpty
    private String senha;
    private int funcao;
	@NotEmpty
    private String hora_entrada;
	@NotEmpty
    private String hora_saida;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private boolean ativo;
	@OneToOne
    private Cliente cliente;
    @OneToMany(cascade=CascadeType.ALL)
    private List<ServicosExecutados> executados;

	public List<ServicosExecutados> getExecutados() {
		return executados;
	}

	public void setExecutados(List<ServicosExecutados> executados) {
		this.executados = executados;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario() {
    }

    public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }
    
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
