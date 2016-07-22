package sk.ness.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaHelper {
	/*
	 * postarat sa o ziskanie factory umoznit ziskanie entity manager zacatie
	 * transakcie, ukoncenie transakcie uzatvorit faktory aj entity manager
	 */

	private static EntityManagerFactory factory;
	private static EntityManager entityManager;

	private static EntityManagerFactory getFactory() {
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		}
		return factory;
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = getFactory().createEntityManager();
		}
		return entityManager;
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void commitTransaction() {
		getEntityManager().getTransaction().commit();
	}

	public static void closeEntityManager() {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
	}

	private static void closeEntityManagerFactory() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
	}

	public static void closeAll() {
		closeEntityManager();
		closeEntityManagerFactory();
	}
}
