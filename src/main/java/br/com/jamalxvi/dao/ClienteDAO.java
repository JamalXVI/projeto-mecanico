package br.com.jamalxvi.dao;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.FotoPerfil;
import br.com.jamalxvi.modelo.IdentificacaoVeiculo;
import br.com.jamalxvi.modelo.TelefoneCliente;
@RequestScoped
public class ClienteDAO {
	
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.ClienteDAO clienteDAO;
	public ClienteDAO()
	{
		this(null, null);
	}
	@Inject
	public ClienteDAO(EntityManager manager, br.com.jamalxvi.manual_dao.ClienteDAO clienteDAO){
		this.manager = manager;
		this.clienteDAO = clienteDAO;
	}
	
	public void salvar(Cliente cliente, byte[] imagem_perfil, List<TelefoneCliente> telefones )
	{
		manager.getTransaction().begin();
		cliente.setAtivo(true);
		cliente.getIdentificacao().setCliente(cliente);
		manager.persist(cliente);
		
		if (telefones != null) {
			if (telefones.size() > 0) {
				for (TelefoneCliente telefone : telefones) {
					telefone.setCliente(cliente);
					telefone.setAtivo(true);
					manager.persist(telefone);
				}
			}
		}
		if (imagem_perfil != null) {

			FotoPerfil perfil = new FotoPerfil();
			perfil.setAtivo(true);
			perfil.setCliente(cliente);
			perfil.setImg(imagem_perfil);
			manager.persist(perfil);
		}
		manager.flush();
		manager.getTransaction().commit();manager.clear();
	}
	public int ultimo()
	{
		Cliente cliente = clienteDAO.ultimo();
		return cliente.getId();
	}
	public void atualizar(Cliente cliente, byte[] imagem_perfil, List<TelefoneCliente> telefones )
	{
		manager.getTransaction().begin();
		cliente.setAtivo(true);
		cliente.getIdentificacao().setCliente(cliente);
		manager.merge(cliente);
		if (telefones != null) {
			if (telefones.size() > 0) {
				for (TelefoneCliente telefone : telefones) {
					telefone.setCliente(cliente);
					telefone.setAtivo(true);
					manager.merge(telefone);
				}
			}
		}

		if (imagem_perfil != null) {

			FotoPerfil perfil = new FotoPerfil();
			perfil.setAtivo(true);
			perfil.setCliente(cliente);
			perfil.setImg(imagem_perfil);
			manager.merge(perfil);
		}
		manager.flush();
		manager.getTransaction().commit();manager.clear();
		
	}
	
	public Cliente pesquisar_id(int id)
	{
		Cliente cliente = clienteDAO.cliente(id);
	    cliente = clienteDAO.preparar_cliente(cliente);
		return cliente;
	}
	public Cliente pesquisar_id_normal(int id)
	{
		return manager.find(Cliente.class, id);
	}
	public void apagar_telefones(int id)
	{
		Cliente cliente = pesquisar_id(id);
		List<TelefoneCliente> telefoneCliente = cliente.getTelefones();
		manager.getTransaction().begin();
			for (TelefoneCliente tel : telefoneCliente) {
				tel.setAtivo(false);
				manager.merge(tel);
			}
		manager.getTransaction().commit();manager.clear();
		
	}
	public Collection<Cliente> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		Collection<Cliente> clientes = clienteDAO.cliente_nome(pesquisa);
				new br.com.jamalxvi.manual_dao.ExecutadoDAO();
		for (Cliente cliente : clientes) {
			cliente = clienteDAO.preparar_cliente(cliente);
		}
		return clientes;
		
	}
	public Collection<Cliente> listar()
	{
		Collection<Cliente> clientes = clienteDAO.clientes();
		for (Cliente cliente : clientes) {
			cliente = clienteDAO.preparar_cliente(cliente);
		}
		return clientes;
		
	}
	public void apagar_imagens(int id)
	{
		Cliente cliente = pesquisar_id(id);
		List<FotoPerfil> fotos = cliente.getFotos();
		manager.getTransaction().begin();
			for (FotoPerfil foto : fotos) {
				foto.setAtivo(false);
				manager.merge(foto);
			}
		manager.getTransaction().commit();manager.clear();
		
	}
	public void apagar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Cliente> query = manager.createQuery("Select c from Cliente c where c.id = :pId",
				Cliente.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Cliente cliente = query.getResultList().get(0);
		cliente.setAtivo(false);
		for (IdentificacaoVeiculo veiculo : cliente.getVeiculos()) {
			veiculo.setAtivo(false);
		}
		manager.getTransaction().commit();manager.clear();
	}
	public Collection<Cliente> pesquisar_informacao(String pesquisa) {
		if (pesquisa == null) {
			pesquisa = "";
		}
		Collection<Cliente> clientes = clienteDAO.cliente_informacao(pesquisa);
		for (Cliente cliente : clientes) {
			cliente = clienteDAO.preparar_cliente(cliente);
		}
		return clientes;
	}
	public Collection<Cliente> pesquisar_identidade(String pesquisa) {
		if (pesquisa == null) {
			pesquisa = "";
		}
		Collection<Cliente> clientes = clienteDAO.cliente_identidade(pesquisa);
		for (Cliente cliente : clientes) {
			cliente = clienteDAO.preparar_cliente(cliente);
		}
		return clientes;
	}
}