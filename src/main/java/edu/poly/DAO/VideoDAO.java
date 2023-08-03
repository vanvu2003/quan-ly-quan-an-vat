package edu.poly.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import javax.transaction.Transactional;

import edu.poly.entity.Video;
import edu.poly.helpdb.databaseHelp;

public class VideoDAO {
	public List<Video> fillAll() {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "select v from Video v";

		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);

		return query.getResultList();
	}

	public void insert(Video video) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.persist(video);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public void update(Video vd) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction tras = em.getTransaction();

		try {
			tras.begin();
			em.merge(vd);
			tras.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tras.rollback();
		}
	}

	public void delete(Video video) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.remove(em.contains(video) ? video : em.merge(video));

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public long countVideo() {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "SELECT COUNT(v) FROM Video v";

		TypedQuery<Long> quyery = em.createQuery(jqpl, Long.class);

		return quyery.getSingleResult();
	}

	public Video findtVideoByID(int id) {
		EntityManager em = databaseHelp.getEntityManager();

		TypedQuery<Video> query = em.createQuery("select v from Video v where v.idVideo = :id", Video.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public Video findVideo(Long id) {
		EntityManager em = databaseHelp.getEntityManager();

		TypedQuery<Video> query = em.createQuery("select v from Video v where v.idVideo = :id", Video.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public void updateView(Video video) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(video);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public List<Video> videoRandom() {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "SELECT v FROM Video v ORDER BY NEWID()";

		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setMaxResults(4);

		return query.getResultList();
	}

	public List<Video> pagingVideo(int index) {
		EntityManager em = databaseHelp.getEntityManager();

		int pageSize = 8; // số lượng vide hiển thị
		int firsResult = (index - 1) * pageSize; // Vị trí bắt đầu

		String jqpl = "SELECT v FROM Video v";

		TypedQuery<Video> query = em.createQuery(jqpl, Video.class).setFirstResult(firsResult).setMaxResults(pageSize);

		return query.getResultList();

	}
}
