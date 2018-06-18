package br.com.jamalxvi.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.modelo.FotoPerfil;
import br.com.jamalxvi.modelo.Funcionario;
import br.com.jamalxvi.modelo.IdentificacaoVeiculo;
import br.com.jamalxvi.modelo.TelefoneCliente;

@RequestScoped
public class FuncionarioDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.FuncionarioDAO funcionarioDAO;
	public FuncionarioDAO()
	{
		this(null, null);
	}
	@Inject
	public FuncionarioDAO(EntityManager manager, br.com.jamalxvi.manual_dao.FuncionarioDAO funcionarioDAO){
		this.manager = manager;
		this.funcionarioDAO = funcionarioDAO;
	}
	public void salvar(Funcionario funcionario, byte[] imagem_perfil, List<TelefoneCliente> telefones )
	{
		manager.getTransaction().begin();
		funcionario.setSenha(md5(funcionario.getSenha()));
		funcionario.setAtivo(true);
		funcionario.getCliente().setAtivo(true);
		funcionario.getCliente().getIdentificacao().setCliente(funcionario.getCliente());
		manager.persist(funcionario.getCliente());
		manager.persist(funcionario);
		if (telefones != null) {
			if (telefones.size() > 0) {
				for (TelefoneCliente telefone : telefones) {
					telefone.setCliente(funcionario.getCliente());
					telefone.setAtivo(true);
					manager.persist(telefone);
				}
			}
		}
		if (imagem_perfil != null) {

			FotoPerfil perfil = new FotoPerfil();
			perfil.setAtivo(true);
			perfil.setCliente(funcionario.getCliente());
			perfil.setImg(imagem_perfil);
			manager.persist(perfil);
		}
		manager.flush();
		manager.getTransaction().commit();manager.clear();
		
	}
	public Collection<Funcionario> lista(){
		Collection<Funcionario> funcionarios = funcionarioDAO.funcionarios();
		return funcionarios;
	}
	public void atualizar(Funcionario funcionario, byte[] imagem_perfil, List<TelefoneCliente> telefones )
	{
		manager.getTransaction().begin();
		funcionario.setSenha(md5(funcionario.getSenha()));
		funcionario.setAtivo(true);
		funcionario.getCliente().setAtivo(true);
		funcionario.getCliente().getIdentificacao().setCliente(funcionario.getCliente());
		manager.merge(funcionario.getCliente());
		manager.merge(funcionario);
		if (telefones != null) {
			if (telefones.size() > 0) {
				for (TelefoneCliente telefone : telefones) {
					telefone.setCliente(funcionario.getCliente());
					telefone.setAtivo(true);
					manager.merge(telefone);
				}
			}
		}

		if (imagem_perfil != null) {

			FotoPerfil perfil = new FotoPerfil();
			perfil.setAtivo(true);
			perfil.setCliente(funcionario.getCliente());
			perfil.setImg(imagem_perfil);
			manager.merge(perfil);
		}
		/*
		funcionario.getCliente().getIdentificacao().setCliente(funcionario.getCliente());
		Identificacao i = funcionario.getCliente().getIdentificacao();
		manager.merge(i);
		*/
		manager.getTransaction().commit();manager.clear();
		
	}
	public Funcionario pesquisar_id(int id)
	{
		Funcionario funcionario = funcionarioDAO.funcionario(id);
		return funcionario;
	}
	public Funcionario ultimo()
	{
		return funcionarioDAO.ultimo();
	}
	public Funcionario pesquisar_id_normal(int id)
	{
		return manager.find(Funcionario.class, id);
	}
	public boolean verificar_se_eh_funcionario(int id)
	{
		
		return funcionarioDAO.funcionario_eh_cliente_id(id);
	}
	public Funcionario cliente_funcionario(int id)
	{
		Funcionario funcionario = new br.com.jamalxvi.manual_dao.FuncionarioDAO().funcionario_cliente_id(id);
		
		return funcionario;
	}
	public Collection<Funcionario> logou(String usuario, String senha)
	{
		senha = md5(senha);
		TypedQuery<Funcionario> query = manager.createQuery("Select f from Funcionario f where f.usuario LIKE :pUsuario"
				+ " and f.senha	LIKE :pSenha", Funcionario.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pUsuario", usuario);
		query.setParameter("pSenha", senha);
		Collection<Funcionario> list = query.getResultList();
		
		return list;
	}
	public void apagar_telefones(int id)
	{
		Funcionario funcionario = pesquisar_id(id);
		List<TelefoneCliente> telefoneCliente = funcionario.getCliente().getTelefones();
		manager.getTransaction().begin();
			for (TelefoneCliente tel : telefoneCliente) {
				tel.setAtivo(false);
				manager.merge(tel);
			}
		manager.getTransaction().commit();manager.clear();
		
	}
	public void apagar_imagens(int id)
	{
		Funcionario funcionario = pesquisar_id(id);
		List<FotoPerfil> fotos = funcionario.getCliente().getFotos();
		manager.getTransaction().begin();
			for (FotoPerfil foto : fotos) {
				foto.setAtivo(false);
				manager.merge(foto);
			}
		manager.getTransaction().commit();manager.clear();
		
	}
	public void deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Funcionario> query = manager.createQuery("Select f from Funcionario f where "
				+ "f.id = :pId", Funcionario.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Funcionario funcionario = query.getResultList().get(0);
		funcionario.setAtivo(false);
		funcionario.getCliente().setAtivo(false);
		for(IdentificacaoVeiculo veiculo : funcionario.getCliente().getVeiculos())
		{
			veiculo.setAtivo(false);
		}
		manager.getTransaction().commit();manager.clear();
	}
	private String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
}
