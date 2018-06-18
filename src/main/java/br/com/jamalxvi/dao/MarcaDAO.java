package br.com.jamalxvi.dao;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jamalxvi.modelo.Marca;
import br.com.jamalxvi.modelo.Modelo;
@RequestScoped
public class MarcaDAO {
	private EntityManager manager;
	private br.com.jamalxvi.manual_dao.MarcaDAO marcaDAO;
	public MarcaDAO(){
		this(null, null);
	}
	@Inject
	public MarcaDAO(EntityManager manager, br.com.jamalxvi.manual_dao.MarcaDAO marcaDAO){
		this.manager = manager;
		this.marcaDAO = marcaDAO;
	}
	public void salvar(Marca marca)
	{
		marca.setAtivo(true);
		manager.getTransaction().begin();
		manager.persist(marca);
		manager.flush();
		manager.getTransaction().commit();manager.clear();
		
	}
	public void editar(Marca marca)
	{
		marca.setAtivo(true);
		manager.getTransaction().begin();
		manager.merge(marca);
		manager.getTransaction().commit();manager.clear();
		
	}
	public Collection<Marca> listar()
	{
		Collection<Marca> marcas = marcaDAO.marcas();
		for (Marca marca : marcas) {
			//marca.setModelo(modeloDAO.marca_id(marca.getId()));
			marca = marcaDAO.preparar_marca(marca);
		}
		return marcas;
	}
	public Marca buscar_id(int id)
	{
		Marca marca = marcaDAO.marca(id);
		marca = marcaDAO.preparar_marca(marca);
		return marca;
	}
	public int decrescente()
	{
		return marcaDAO.ultimo().getId();
	}
	public Collection<Marca> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		return marcaDAO.marca_nome(pesquisa);
		
	}
	public void apagar(int id)
	{
		manager.getTransaction().begin();
		Marca marca = manager.find(Marca.class, id);
		marca.setAtivo(false);
		for (Modelo modelo : marca.getModelo()) {
			modelo.setAtivo(false);
		}
		manager.getTransaction().commit();
	}
}
