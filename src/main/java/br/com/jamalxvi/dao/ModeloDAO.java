package br.com.jamalxvi.dao;

import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jamalxvi.modelo.Modelo;
@RequestScoped
public class ModeloDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.ModeloDAO modeloDAO;
	public ModeloDAO(){
		this(null, null);
	}
	@Inject
	public ModeloDAO(EntityManager manager, br.com.jamalxvi.manual_dao.ModeloDAO modeloDAO){
		this.manager = manager;
		this.modeloDAO = modeloDAO;
	}
	public void salvar(Modelo modelo)
	{
		manager.getTransaction().begin();
		manager.persist(modelo);
		manager.flush();
		manager.getTransaction().commit();manager.clear();
		
	}
	public void editar(Modelo modelo, int id_marca)
	{
		manager.getTransaction().begin();
		manager.merge(modelo);
		manager.getTransaction().commit();manager.clear();
		
	}
	public Modelo pesquisar_id(int id)
	{
		Modelo modelo = modeloDAO.modelo(id);
		return modelo;
	}
	public Collection<Modelo> listar()
	{
		Collection<Modelo> modelos = modeloDAO.modelos();
		return modelos;
	}
	public int ultimo()
	{
		Modelo modelo = modeloDAO.ultimo();
		return modelo.getId();
	}
	public void apagar(int id)
	{
		manager.getTransaction().begin();
		Modelo modelo = manager.find(Modelo.class, id);
		modelo.setAtivo(false);
		manager.getTransaction().commit();
	}
	public Collection<Modelo> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		return modeloDAO.modelo_nome(pesquisa);
	}
}
