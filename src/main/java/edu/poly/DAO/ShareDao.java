package edu.poly.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import edu.poly.entity.Share;
import edu.poly.helpdb.databaseHelp;

public class ShareDao {
	public void insertShare(Share share) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.persist(share);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}
}
