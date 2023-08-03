package edu.poly.DAO;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.poly.entity.User;
import edu.poly.helpdb.databaseHelp;

public class UserDAO {
	public List<User> fillAll() {
		EntityManager em = databaseHelp.getEntityManager();
		
		String jqpl = "select u from User u ";
		
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		
		
		return query.getResultList();
	}
	public void insert(User us) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction tras = em.getTransaction();
		
		try {
			tras.begin();
			
			em.persist(us);
			
			tras.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tras.rollback();
		}
	}
	public void delete(User user) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction tras = em.getTransaction();
		
		try {
			tras.begin();
			
			em.remove(em.contains(user) ? user : em.merge(user));
			
			tras.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tras.rollback();
		}
	}
	public User findUserByID(String id) {
		EntityManager em = databaseHelp.getEntityManager();
		
		TypedQuery<User> query = em.createQuery("select u from User u where u.userID = :id", User.class);
		query.setParameter("id", id);
		
		return query.getResultList().stream().findFirst().orElse(null);
		
	}
	
	public Boolean updatePass(User user) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(user);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		return true;
	}
	
	public void update(User user) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(user);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}
	
	public long countUser() {
		EntityManager em = databaseHelp.getEntityManager();
		
		String jqpl = "SELECT COUNT(u) FROM User u";
		
		TypedQuery<Long> quyery = em.createQuery(jqpl,Long.class);
		
		return quyery.getSingleResult();
	}
}
