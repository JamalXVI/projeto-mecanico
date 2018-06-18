package br.com.jamalxvi.infra;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		factory.getCache().evictAll();
		EntityManager manager = factory.createEntityManager();
		manager.clear();
		return manager;
	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}
}