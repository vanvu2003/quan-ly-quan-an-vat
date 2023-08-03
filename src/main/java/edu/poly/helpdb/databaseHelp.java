package edu.poly.helpdb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class databaseHelp {
	public static EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AssignmentJava4");

		return entityManagerFactory.createEntityManager();
	}
	
}
