package edu.poly.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;

import edu.poly.entity.Favorite;
import edu.poly.entity.User;
import edu.poly.entity.Video;
import edu.poly.helpdb.databaseHelp;

public class FavouriteDAO {
	public void insertLike(Favorite favourite) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.persist(favourite);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public void delete(Favorite favorite) {
		EntityManager em = databaseHelp.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.remove(em.contains(favorite) ? favorite : em.merge(favorite));

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	public Favorite findFavourite(String userID, int idVideo) {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "select f from Favorite f where f.user.userID = :idUser and f.video.idVideo = :idVideo";

		TypedQuery<Favorite> query = em.createQuery(jqpl, Favorite.class);
		query.setParameter("idUser", userID);
		query.setParameter("idVideo", idVideo);

		return query.getResultList().stream().findFirst().orElse(null);
	}

	public List<Video> findFavouriteByIdUser(String userID) {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "SELECT v FROM Favorite f JOIN f.video v WHERE f.user.userID = :userID";

		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setParameter("userID", userID);

		return query.getResultList();
	}

	public List<Object[]> selectCountVideo() {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "SELECT v.tiltle, v.views, COUNT(f.id), f.likeDate \r\n" + "FROM Favorite f \r\n"
				+ "INNER JOIN f.video v \r\n" + "GROUP BY v.tiltle, v.views, f.likeDate";

		TypedQuery<Object[]> query = em.createQuery(jqpl, Object[].class);

		return query.getResultList();
	}

	public List<Object[]> selectUserLikeVideo(int idVideo) {
		EntityManager em = databaseHelp.getEntityManager();

		String jqpl = "SELECT u.userID, u.fullname, u.email, f.likeDate \r\n"
				+ "FROM Video v \r\n"
				+ "INNER JOIN v.favorites f \r\n"
				+ "INNER JOIN f.user u \r\n"
				+ "WHERE v.idVideo = :idVideo";

		TypedQuery<Object[]> query = em.createQuery(jqpl,Object[].class);
		query.setParameter("idVideo", idVideo);

		return query.getResultList();
	}
}
