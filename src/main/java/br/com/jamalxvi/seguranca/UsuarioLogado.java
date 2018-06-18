package br.com.jamalxvi.seguranca;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.jamalxvi.modelo.Funcionario;


@SessionScoped
@Named
public class UsuarioLogado implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Funcionario usuario;
    private Calendar horario_login;
    public void fazLogin(Funcionario usuario){
        this.horario_login = Calendar.getInstance();
    	this.usuario = usuario;
        
    }
    public void desloga(){
        this.usuario = null;
    }
    public Funcionario getUsuario() {
        return this.usuario;
    }
    public boolean isLogado() {
        return this.usuario != null;
    }
    public int pegar_funcao()
    {
    	return this.usuario.getFuncao();
    }
	public Calendar getHorario_login() {
		return horario_login;
	}
	public void setHorario_login(Calendar horario_login) {
		this.horario_login = horario_login;
	}
    
}